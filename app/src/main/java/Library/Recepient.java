package Library;

/**
 * Created by ytgv8b on 08.10.2017.
 */

public class Recepient implements IEdite {
    private String _name; //Имя
    private String _number; //Номер

    public Recepient(String name, String number)
    {
        _name = name;
        _number = number;
    }

    public Recepient() {}

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getNumber() {
        return _number;
    }

    public void setNumber(String number) {
        _number = number;
    }

    @Override
    public void OpenEditeDialog() {  //Редактирование
        /****Открытие диалога редактирования****/
    }
}
