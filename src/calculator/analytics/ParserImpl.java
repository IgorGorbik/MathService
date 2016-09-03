package calculator.analytics;

/**
 *
 * @author Игорь
 */
public class ParserImpl implements Parser {

    private int getSygnal(char chr) {

        Character c = chr;
        String str = c.toString();
        byte[] codes = str.getBytes();
        int code = codes[0];

        int ret = 6;

        //открывающая скобка
        if (code == 40) {
            ret = 0;
        }
        //закрывающая скобка
        if (code == 41) {
            ret = 1;
        }

        //цифра
        if (code >= 48 && code <= 57) {
            ret = 2;
        }

        //знак оператора -,+
        if (code == 43 || code == 45) {
            ret = 3;
        }

        //знак оператора *,/,^
        if (code == 42 || code == 47 || code == 94) {
            ret = 4;
        }

        //точка
        if (code == 46) {
            ret = 5;
        }

        return ret;
    }

    @Override
    public boolean accept(String str) {

        boolean accept = true;

        int state = 0;
        int next_state = 0;
        int sygnal = 0;

        int left_b = 0;
        int right_b = 0;

        int len = str.length();
        for (int i = 0; i < len; i++) {

            sygnal = this.getSygnal(str.charAt(i));//код сигнала

            if (sygnal == 0) {
                left_b++; //скобка открылась
            }
            if (sygnal == 1) {
                right_b++; //скобка закрылась
            }

            //реализация перехода из состояния в состояние
            switch (state) {
                case 0:
                    if (sygnal == 0) {
                        next_state = 1;
                    } else if (sygnal == 2) {
                        next_state = 3;
                    } else if (sygnal == 3) {
                        next_state = 6;
                    } else {
                        next_state = 8;
                    }
                    break;
                case 1:
                    if (sygnal == 0) {
                        next_state = 1;
                    } else if (sygnal == 2) {
                        next_state = 3;
                    } else if (sygnal == 3) {
                        next_state = 6;
                    } else {
                        next_state = 8;
                    }
                    break;
                case 2:
                    if (sygnal == 1) {
                        next_state = 2;
                    } else if (sygnal == 3) {
                        next_state = 6;
                    }  else if (sygnal == 4) {
                        next_state = 7;
                    } else {
                        next_state = 8;
                    }
                    break;
                case 3:
                    if (sygnal == 1) {
                        next_state = 2;
                    } else if (sygnal == 2) {
                        next_state = 3;
                    } else if (sygnal == 3) {
                        next_state = 6;
                    } else if (sygnal == 4) {
                        next_state = 7;
                    }  else if (sygnal == 5) {
                        next_state = 4;
                    } else {
                        next_state = 8;
                    }
                    break;
                case 4:
                    if (sygnal == 2) {
                        next_state = 5;
                    } else {
                        next_state = 8;
                    }
                    break;
                case 5:
                    if (sygnal == 1) {
                        next_state = 2;
                    } else if (sygnal == 2) {
                        next_state = 5;
                    } else if (sygnal == 3) {
                        next_state = 6;
                    }  else if (sygnal == 4) {
                        next_state = 7;
                    } else {
                        next_state = 8;
                    }
                    break;
                case 6: case 7:
                    if (sygnal == 0) {
                        next_state = 1;
                    } else if (sygnal == 2) {
                        next_state = 3;
                    } else {
                        next_state = 7;
                    }
                    break;
            }

            if (next_state == 8) {
                accept = false;
                break;
            }

            state = next_state;//переход в следующее состояние
        }

        //проверка на незакрытые скобки
        if (left_b != right_b) {
            accept = false;
        }

        if (next_state == 4 || next_state == 6 || next_state == 7) {
            accept = false;
        }

        return accept;
    }
}
