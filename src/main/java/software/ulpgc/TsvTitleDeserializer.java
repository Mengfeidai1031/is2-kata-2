package software.ulpgc;

public class TsvTitleDeserializer implements  TitleDeserializer{
    @Override
    public Title deserialize(String data) {
        return deserialize(data.split("\t"));
    }

    public Title deserialize(String[] fields) {
        return new Title(
                fields[0],
                toTitleType(fields[1]),
                fields[2]
        );
    }

    private Title.TitleType toTitleType(String field) {
        return Title.TitleType.valueOf(normalize(field));
    }

    private String normalize(String field) {
        String upperCase = field.toUpperCase();
        String temp = field.replace("-","");
        return upperCase.toCharArray()[0] + temp.substring(1);
    }
}
