package mediator;

public class NetworkPackage {
    private String TYPE;
    private String CONTENT;

    public NetworkPackage(String type, String content) {
        this.TYPE = type;
        this.CONTENT = content;
    }
    @Override
    public String toString() {
        return "NetworkPackage{" +
                "type='" + TYPE + '\'' +
                ", content='" + CONTENT + '\'' +
                '}';
    }

    public String getContent() {
        return CONTENT;
    }

    public void setContent(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public String getType() {
        return TYPE;
    }

    public void setType(String TYPE) {
        this.TYPE = TYPE;
    }


}
