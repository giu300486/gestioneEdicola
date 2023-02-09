package it.fornaro.gestione_edicola.views.dialogs;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class DialogGestioneEdicola extends Dialog {

    private VerticalLayout verticalLayout;
    private H2 title;
    private Text message;
    private Button button;

    public DialogGestioneEdicola(String title, String message) {
        super();
        this.verticalLayout = new VerticalLayout();

        this.title = new H2(title);
        this.message = new Text(message);
        this.button = new Button("OK");

        this.button.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                DialogGestioneEdicola.super.close();
            }
        });

        super.setModal(true);
        super.setCloseOnOutsideClick(false);
        super.setCloseOnEsc(false);
        super.setDraggable(true);

        this.verticalLayout.add(this.title,this.message,this.button);
        super.add(verticalLayout);
    }

}
