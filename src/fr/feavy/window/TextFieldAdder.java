package fr.feavy.window;

import java.util.function.Consumer;

interface TextFieldAdder {
    /**
     * Add a new textfield in this window
     * @param value Value of this textfield
     * @param caption Label of this textfield
     */
    StringData add(String value, String caption);

    /**
     * Add a new textfield in this window
     * @param value Value of this textfield
     * @param caption Label of this textfield
     * @param editable Is textfield editable ? Default : True
     */
    StringData add(String value, String caption, boolean editable);

    /**
     * Add a new textfield in this window
     * @param value Value of this textfield
     * @param caption Label of this textfield
     * @param editable Is textfield editable ? Default : True
     * @param onEdit Called when textfield's value changes.
     */
    StringData add(String value, String caption, boolean editable, boolean isPassword, Consumer<String> onEdit);
}
