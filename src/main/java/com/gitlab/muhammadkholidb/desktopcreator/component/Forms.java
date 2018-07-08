package com.gitlab.muhammadkholidb.desktopcreator.component;

import java.net.URL;
import org.apache.pivot.beans.BXMLSerializer;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Form;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Window;

/**
 *
 * @author muhammad
 */
public class Forms extends Window implements Bindable, Application {
    
    private BoxPane nameBoxPane = null;
    private TextInput lastNameTextInput = null;
    private TextInput firstNameTextInput = null;
    private PushButton submitButton = null;
    private Label errorLabel = null;
 
    @Override
    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
        nameBoxPane = (BoxPane)namespace.get("nameBoxPane");
        lastNameTextInput = (TextInput)namespace.get("lastNameTextInput");
        firstNameTextInput = (TextInput)namespace.get("firstNameTextInput");
        submitButton = (PushButton)namespace.get("submitButton");
        errorLabel = (Label)namespace.get("errorLabel");
 
        submitButton.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
                String lastName = lastNameTextInput.getText();
                String firstName = firstNameTextInput.getText();
 
                Form.Flag flag = null;
                if (lastName.length() == 0
                    || firstName.length() == 0) {
                    flag = new Form.Flag(MessageType.ERROR, "Name is required.");
                }
 
                Form.setFlag(nameBoxPane, flag);
 
                if (flag == null) {
                    errorLabel.setText("");
                    Prompt.prompt("Pretending to submit...", Forms.this);
                } else {
                    errorLabel.setText("Some required information is missing.");
                }
            }
        });
    }
    
    private Window window;
    
    @Override
    public void startup(Display dspl, Map<String, String> map) throws Exception {
        BXMLSerializer bxmlSerializer = new BXMLSerializer();
        window = (Window) bxmlSerializer.readObject(Forms.class.getResource("forms.bxml"));
        window.open(dspl);
    }

    @Override
    public boolean shutdown(boolean bln) throws Exception {
        this.close();
        return false;
    }

    @Override
    public void suspend() throws Exception {

    }

    @Override
    public void resume() throws Exception {

    }

}