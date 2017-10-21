package Library;

import Library.Interfaces.IEdite;
import Library.Interfaces.IMessageRecepient;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class Recepient implements IEdite, IMessageRecepient {
    /****Методы интерфейсов****/
    private String name; //Имя
    private String number; //Номер

    public Recepient(String name, String number)
    {
        this.name = name;
        this.number = number;
    }

    public Recepient() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public void OpenEditeDialog() {  //Редактирование
        /****Открытие диалога редактирования****/
    }
}
