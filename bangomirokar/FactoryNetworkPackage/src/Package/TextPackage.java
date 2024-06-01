package Package;

public class TextPackage extends NetworkPackage{
    private String text;
    public TextPackage(String type, String text) {
        super(type);
        this.text = text;
    }
    public String getText(){
        return this.text;
    }
    @Override
    public String toString(){
        return "TextPackage t: " + super.getType() + ", text: " + text;
    }
}
