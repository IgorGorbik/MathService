package parser;

import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLMenuLoader {

    // источник данных XML 
    private InputSource source;
    // анализатор XML 
    private SAXParser parser;
    // обработчик XML 
    private DefaultHandler documentHandler;
    // хранилище для всех частей системы меню
    private Map menuStorage = new HashMap();

    // конструктор, требует задать поток данных с меню
    public XMLMenuLoader(InputStream is) throws UnsupportedEncodingException {
        try {
            Reader reader = new InputStreamReader(is, "UTF-8");
            source = new InputSource(reader);
            parser = SAXParserFactory.newInstance().newSAXParser();
        } catch (ParserConfigurationException | SAXException ex) {
            throw new RuntimeException(ex);
        }
        documentHandler = new XMLParser();
    }

    // считывает XML и создает систему меню
    public void parse() throws Exception {
        parser.parse(source, documentHandler);
    }

    // позволяет получить строку меню
    public JMenuBar getMenuBar(String name) {
        return (JMenuBar) menuStorage.get(name);
    }

    // позволяет получить выпадающее меню
    public JMenu getMenu(String name) {
        return (JMenu) menuStorage.get(name);
    }

    // позволяет получить элемент меню
    public JMenuItem getMenuItem(String name) {
        return (JMenuItem) menuStorage.get(name);
    }

    // удобный метод для быстрого добавления слушателя событий
    public void addActionListener(String name, ActionListener listener) {
        getMenuItem(name).addActionListener(listener);
    }
    // текущая строка меню
    private JMenuBar currentMenuBar;
    // список для упорядочения выпадающих меню
    private LinkedList menus = new LinkedList();

    // обработчик XML 
    class XMLParser extends DefaultHandler {

        // новый узел XML 
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            // определяем тип узла
            switch (qName) {
                case "menubar":
                    parseMenuBar(attributes);
                    break;
                case "menu":
                    parseMenu(attributes);
                    break;
                case "menuitem":
                    parseMenuItem(attributes);
                    break;
                default:
                    break;
            }
        }

        // конец узла, используется для смены выпадающих меню
        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equals("menu")) {
                menus.removeFirst();
            }
        }

        // создает новую строку меню
        protected void parseMenuBar(Attributes attrs) {
            JMenuBar menuBar = new JMenuBar();
            // определяем имя
            String name = attrs.getValue("name");
            menuStorage.put(name, menuBar);
            currentMenuBar = menuBar;
        }

        // создает новое выпадающее меню
        protected void parseMenu(Attributes attrs) {
            // создаем меню
            JMenu menu = new JMenu();
            String name = attrs.getValue("name");
            // настраиваем общие атрибуты
            adjustProperties(menu, attrs);
            menuStorage.put(name, menu);
            // добавляем меню к предыдущему выпадающему меню или к строке меню
            if (!menus.isEmpty()) {
                ((JMenu) menus.getFirst()).add(menu);
            } else {
                currentMenuBar.add(menu);
            }
            // добавляем в список выпадающих меню
            menus.addFirst(menu);
        }

        // новый пункт меню
        protected void parseMenuItem(Attributes attrs) {
            // проверяем, не разделитель ли это
            String name = attrs.getValue("name");
            if (name.equals("separator")) {
                ((JMenu) menus.getFirst()).addSeparator();
                return;
            }
            // создаем пункт меню
            JMenuItem menuItem = new JMenuItem();
            // настраиваем свойства
            adjustProperties(menuItem, attrs);
            menuStorage.put(name, menuItem);
            // добавляем к текущему выпадающему меню
            ((JMenu) menus.getFirst()).add(menuItem);
        }

        // настройка общих атрибутов пунктов меню
        private void adjustProperties(JMenuItem menuItem, Attributes attrs) {
            // получаем поддерживаемые атрибуты
            String text = attrs.getValue("text");
            String mnemonic = attrs.getValue("mnemonic");
            String accelerator = attrs.getValue("accelerator");
            String enabled = attrs.getValue("enabled");
            // настраиваем свойства меню
            menuItem.setText(text);
            if (mnemonic != null) {
                menuItem.setMnemonic(mnemonic.charAt(0));
            }
            if (accelerator != null) {
                menuItem.setAccelerator(
                        KeyStroke.getKeyStroke(accelerator));
            }
            if (enabled != null) {
                boolean isEnabled = true;
                if (enabled.equals("false")) {
                    isEnabled = false;
                }
                menuItem.setEnabled(isEnabled);
            }
        }
    }
}
