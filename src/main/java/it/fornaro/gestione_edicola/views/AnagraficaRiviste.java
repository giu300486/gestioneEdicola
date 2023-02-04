package it.fornaro.gestione_edicola.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.StreamResource;
import it.fornaro.gestione_edicola.components.MenuBarTooltip;

public class AnagraficaRiviste extends Composite<Component> {

    private MenuBarTooltip menuBarTooltip;
    private VerticalLayout container;
    private HorizontalLayout northContainer;
    private VerticalLayout centralContainer;
    private HorizontalLayout southContainer;

    @Override
    protected Component initContent() {
        this.container = new VerticalLayout();

        this.northContainer = new HorizontalLayout();
        this.menuBarTooltip = new MenuBarTooltip();
        this.northContainer.add(this.menuBarTooltip);
        this.northContainer.setMinWidth(50,Unit.PERCENTAGE);

        this.centralContainer = new VerticalLayout();
        HorizontalLayout horizontalLayoutBarCode = new HorizontalLayout();
        Label labelBarCode = new Label("Barcode");
        TextField textFieldBarCode = new TextField();
        horizontalLayoutBarCode.add(labelBarCode,textFieldBarCode);
        horizontalLayoutBarCode.setMinWidth(50,Unit.PERCENTAGE);
        horizontalLayoutBarCode.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        //horizontalLayoutBarCode.setFlexGrow(1,labelBarCode);
        //horizontalLayoutBarCode.setFlexGrow(2,textFieldBarCode);

        HorizontalLayout horizontalLayoutDescrizione = new HorizontalLayout();
        Label labelDescrizione = new Label("Descrizione");
        TextField textFieldDescrizione = new TextField();
        horizontalLayoutDescrizione.add(labelDescrizione,textFieldDescrizione);
        horizontalLayoutDescrizione.setMinWidth(50,Unit.PERCENTAGE);
        horizontalLayoutDescrizione.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        //horizontalLayoutDescrizione.setFlexGrow(1,labelDescrizione);
        //horizontalLayoutDescrizione.setFlexGrow(2,textFieldDescrizione);

        HorizontalLayout horizontalLayoutPrezzo = new HorizontalLayout();
        Label labelPrezzo = new Label("Prezzo");
        NumberField numberFieldPrezzo = new NumberField();
        numberFieldPrezzo.setHasControls(true);
        numberFieldPrezzo.setMin(0.0);
        numberFieldPrezzo.setMax(5.0);
        numberFieldPrezzo.setStep(0.5);
        numberFieldPrezzo.setClearButtonVisible(true);
        numberFieldPrezzo.setHelperText("From 0.0 to 5.0");
        horizontalLayoutPrezzo.add(labelPrezzo,numberFieldPrezzo);
        horizontalLayoutPrezzo.setMinWidth(50,Unit.PERCENTAGE);
        horizontalLayoutPrezzo.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        //horizontalLayoutPrezzo.setFlexGrow(1,labelPrezzo);
        //horizontalLayoutPrezzo.setFlexGrow(2,numberFieldPrezzo);

        HorizontalLayout horizontalLayoutPeriodo = new HorizontalLayout();
        Label labelPeriodo = new Label("Periodo");
        ComboBox<String> comboBoxPeriodo = new ComboBox<>();
        comboBoxPeriodo.setItems("Giornaliero","Settimanale","Mensile","Trimestrale","Semestrale","Annuale");
        horizontalLayoutPeriodo.add(labelPeriodo,comboBoxPeriodo);
        horizontalLayoutPeriodo.setMinWidth(50,Unit.PERCENTAGE);
        horizontalLayoutPeriodo.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);


        this.centralContainer.add(horizontalLayoutBarCode,horizontalLayoutDescrizione,horizontalLayoutPrezzo,horizontalLayoutPeriodo);

        this.southContainer = new HorizontalLayout();
        StreamResource confermaStreamResource = new StreamResource("conferma",
                () -> getClass().getResourceAsStream("/static/conferma.gif"));
        StreamResource annullaStreamResource = new StreamResource("annulla",
                () -> getClass().getResourceAsStream("/static/annulla.gif"));

        Button conferma = new Button(new Image(confermaStreamResource, "Conferma"));
        Button annulla = new Button(new Image(annullaStreamResource, "Annulla"));
        conferma.setEnabled(false);
        annulla.setEnabled(false);

        this.southContainer.add(conferma,annulla);
        this.southContainer.setMinWidth(50,Unit.PERCENTAGE);
        this.southContainer.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        this.container.add(this.northContainer,this.centralContainer,this.southContainer);

        return this.container;
    }
}
