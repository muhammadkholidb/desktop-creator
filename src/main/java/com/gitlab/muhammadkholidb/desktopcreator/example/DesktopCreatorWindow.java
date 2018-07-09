package com.gitlab.muhammadkholidb.desktopcreator.example;

import java.io.File;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.FileBrowserSheet;
import org.apache.pivot.wtk.Form;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.Sheet;
import org.apache.pivot.wtk.SheetCloseListener;
import org.apache.pivot.wtk.Window;

/**
 *
 * @author muhammad
 */
public class DesktopCreatorWindow extends Window implements Application {

    private final Form form = new Form();
    private final Form.Section section = new Form.Section();
    private final Label label = new Label("Select file to upload...");
    private final PushButton btOpenFileDialog = new PushButton();
    private final FileBrowserSheet fileBrowser = new FileBrowserSheet();

    public DesktopCreatorWindow() {
        compose();
    }

    private void compose() {
        section.add(label);
        btOpenFileDialog.setButtonData("Select File..");
        section.add(btOpenFileDialog);
        form.getSections().add(section);
        fileBrowser.setMode(FileBrowserSheet.Mode.SAVE_AS);
        btOpenFileDialog.getButtonPressListeners().add(fileDialogDisplayListener);
        this.setContent(form);
        this.setTitle("hasCode.com - Apache Pivot Example 1 - Programmatic construction");
        this.setMaximized(true);
    }

    private final ButtonPressListener fileDialogDisplayListener = new ButtonPressListener() {
        @Override
        public void buttonPressed(final Button button) {
            fileBrowser.open(DesktopCreatorWindow.this, new SheetCloseListener() {
                @Override
                public void sheetClosed(final Sheet sheet) {
                    if (sheet.getResult()) {
                        Sequence<File> selectedFiles = fileBrowser.getSelectedFiles();
                        ListView listView = new ListView();
                        listView.setListData(new ArrayList<>(selectedFiles));
                        listView.setSelectMode(ListView.SelectMode.NONE);
                        listView.getStyles().put("backgroundColor", null);
                        Alert.alert(MessageType.INFO, "Files selected for upload:", listView, DesktopCreatorWindow.this);
                    } else {
                        Alert.alert(MessageType.INFO, "No files selected for upload.", DesktopCreatorWindow.this);
                    }
                }
            });
        }
    };

    @Override
    public void startup(Display dspl, Map<String, String> map) throws Exception {
        this.open(dspl);
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
