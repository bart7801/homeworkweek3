package com.bart.homeworkweek3.hello;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("hello1")
public class HelloGui extends VerticalLayout {

    public HelloGui() {
        TextField placeholderField = new TextField();
        placeholderField.setPlaceholder("name");

        Button rightButton = new Button("Say Hello!",
                new Icon(VaadinIcon.HANDSHAKE));
        rightButton.setIconAfterText(true);

        Dialog dialog = new Dialog();
        dialog.setWidth("400px");
        dialog.setHeight("150px");
        rightButton.addClickListener(event -> {
                    dialog.add(new Label("Hello " + placeholderField.getValue()));
                    dialog.open();
                }
        );

        add(placeholderField);
        add(rightButton);
    }
}
