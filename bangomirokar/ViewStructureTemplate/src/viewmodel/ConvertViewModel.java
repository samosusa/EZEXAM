package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class ConvertViewModel {
    private Model model;

    private StringProperty request;
    private StringProperty reply;
    private StringProperty error;
    public ConvertViewModel(Model model){
        this.model = model;
        this.request = new SimpleStringProperty();
        this.reply = new SimpleStringProperty();
        this.error = new SimpleStringProperty();
    }
    public void convert(){
        try {
            reply.set(model.convert(request.get()));
        } catch (Exception e) {
            error.set("Damn");
        }
    }
    public String getRequest() {
        return request.get();
    }

    public StringProperty requestProperty() {
        return request;
    }

    public String getReply() {
        return reply.get();
    }

    public StringProperty replyProperty() {
        return reply;
    }

    public String getError() {
        return error.get();
    }

    public StringProperty errorProperty() {
        return error;
    }


    public void clear(){
        request.set("");
        reply.set("");
        error.set("");
    }

}
