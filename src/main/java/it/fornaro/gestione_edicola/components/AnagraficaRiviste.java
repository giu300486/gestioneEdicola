package it.fornaro.gestione_edicola.components;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.*;
import com.vaadin.flow.data.validator.RegexpValidator;
import com.vaadin.flow.server.StreamResource;
import it.fornaro.gestione_edicola.model.Periodo;
import it.fornaro.gestione_edicola.model.Rivista;
import it.fornaro.gestione_edicola.service.RivistaService;
import it.fornaro.gestione_edicola.views.dialogs.DialogGestioneEdicola;
import java.util.Objects;

public class AnagraficaRiviste extends ContainerTabArchivio {

    private ContainerMenuToolTipTabArchivio northContainer;
    private VerticalLayout centralContainer;
    private HorizontalLayout southContainer;

    private TextField textFieldBarCode;
    private TextField textFieldDescrizione;
    private NumberField numberFieldPrezzo;
    private ComboBox<Periodo> comboBoxPeriodo;
    private Button conferma;
    private Button annulla;

    private Binder<Rivista> binder;
    private Binder<Rivista> binderBarcode;

    public AnagraficaRiviste(Rivista rivista, RivistaService rivistaService, TabsEdicola tabsEdicola) {
        super(tabsEdicola,rivistaService);

        this.textFieldBarCode = new TextField();
        this.textFieldDescrizione = new TextField();
        this.numberFieldPrezzo = new NumberField();
        this.comboBoxPeriodo = new ComboBox<>();

        this.textFieldBarCode.setEnabled(false);
        this.textFieldDescrizione.setEnabled(false);


        this.numberFieldPrezzo.setHasControls(true);
        this.numberFieldPrezzo.setMin(0.0);
        this.numberFieldPrezzo.setMax(5.0);
        this.numberFieldPrezzo.setStep(0.5);
        this.numberFieldPrezzo.setClearButtonVisible(true);
        this.numberFieldPrezzo.setHelperText("From 0.0 to 5.0");
        this.numberFieldPrezzo.setEnabled(false);

        this.comboBoxPeriodo.setItems(Periodo.values());
        this.comboBoxPeriodo.setEnabled(false);

        this.binder = new Binder<>();
        binder.forField(this.textFieldBarCode)
                .withValidator(new RegexpValidator("Inserire un corretto bar code", "^(\\d{13})$"))
                .bind(Rivista::getBarCode, Rivista::setBarCode);
        binder.forField(this.textFieldDescrizione)
                .asRequired("Inserire il nome della rivista")
                .bind(Rivista::getDescrizione, Rivista::setDescrizione);
        binder.forField(this.numberFieldPrezzo)
                .asRequired("Inserire il prezzo")
                .bind(Rivista::getPrezzo, Rivista::setPrezzo);
        binder.forField(this.comboBoxPeriodo)
                .asRequired("Inserire il periodo")
                .bind(Rivista::getPeriodo, Rivista::setPeriodo);
        binder.setBean(rivista);

        this.binderBarcode = new Binder<>();
        binderBarcode.forField(this.textFieldBarCode)
                .withValidator(new RegexpValidator("Inserire un corretto bar code", "^(\\d{13})$"))
                .bind(Rivista::getBarCode, Rivista::setBarCode);

    }

    @Override
    protected Component initContent() {

        this.northContainer = new ContainerMenuToolTipTabArchivio(tabsEdicola);
        this.northContainer.getContent();

        this.centralContainer = new VerticalLayout();
        HorizontalLayout horizontalLayoutBarCode = new HorizontalLayout();
        Label labelBarCode = new Label("Barcode");
        horizontalLayoutBarCode.add(labelBarCode,textFieldBarCode);
        horizontalLayoutBarCode.setMinWidth(50,Unit.PERCENTAGE);
        horizontalLayoutBarCode.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        //horizontalLayoutBarCode.setFlexGrow(1,labelBarCode);
        //horizontalLayoutBarCode.setFlexGrow(2,textFieldBarCode);

        HorizontalLayout horizontalLayoutDescrizione = new HorizontalLayout();
        Label labelDescrizione = new Label("Descrizione");
        horizontalLayoutDescrizione.add(labelDescrizione,textFieldDescrizione);
        horizontalLayoutDescrizione.setMinWidth(50,Unit.PERCENTAGE);
        horizontalLayoutDescrizione.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        //horizontalLayoutDescrizione.setFlexGrow(1,labelDescrizione);
        //horizontalLayoutDescrizione.setFlexGrow(2,textFieldDescrizione);

        HorizontalLayout horizontalLayoutPrezzo = new HorizontalLayout();
        Label labelPrezzo = new Label("Prezzo");
        horizontalLayoutPrezzo.add(labelPrezzo,numberFieldPrezzo);
        horizontalLayoutPrezzo.setMinWidth(50,Unit.PERCENTAGE);
        horizontalLayoutPrezzo.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        //horizontalLayoutPrezzo.setFlexGrow(1,labelPrezzo);
        //horizontalLayoutPrezzo.setFlexGrow(2,numberFieldPrezzo);

        HorizontalLayout horizontalLayoutPeriodo = new HorizontalLayout();
        Label labelPeriodo = new Label("Periodo");
        horizontalLayoutPeriodo.add(labelPeriodo,comboBoxPeriodo);
        horizontalLayoutPeriodo.setMinWidth(50,Unit.PERCENTAGE);
        horizontalLayoutPeriodo.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);


        this.centralContainer.add(horizontalLayoutBarCode,horizontalLayoutDescrizione,horizontalLayoutPrezzo,horizontalLayoutPeriodo);

        this.southContainer = new HorizontalLayout();
        StreamResource confermaStreamResource = new StreamResource("conferma",
                () -> getClass().getResourceAsStream("/static/conferma.gif"));
        StreamResource annullaStreamResource = new StreamResource("annulla",
                () -> getClass().getResourceAsStream("/static/annulla.gif"));

        this.conferma = new Button(new Image(confermaStreamResource, "Conferma"));
        this.annulla = new Button(new Image(annullaStreamResource, "Annulla"));
        this.conferma.setEnabled(false);
        this.annulla.setEnabled(false);

        this.conferma.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                AnagraficaRiviste.this.binder.validate();
                if(!AnagraficaRiviste.this.binder.isValid()) {
                    DialogGestioneEdicola dialogGestioneEdicola = new DialogGestioneEdicola("Messaggio", "Per favore sistemare gli errori");
                    dialogGestioneEdicola.open();
                }
                else {
                    if(TabsEdicola.STATE == 1) {
                        try {
                            Rivista rivistaSaved = AnagraficaRiviste.this.rivistaService.salva(AnagraficaRiviste.this.binder.getBean());
                            if (!Objects.isNull(rivistaSaved)) {
                                DialogGestioneEdicola dialogGestioneEdicola = new DialogGestioneEdicola("Messaggio", "Rivista " + binder.getBean().getDescrizione() + " salvata con successo");
                                dialogGestioneEdicola.open();
                            } else {
                                DialogGestioneEdicola dialogGestioneEdicola = new DialogGestioneEdicola("Messaggio", "Errore nel salvare la rivista " + binder.getBean().getDescrizione() + ". Bar code duplicato");
                                dialogGestioneEdicola.open();
                            }
                        } catch (Exception e) {
                            DialogGestioneEdicola dialogGestioneEdicola = new DialogGestioneEdicola("Messaggio", "Errore nel salvare la rivista " + binder.getBean().getDescrizione());
                            dialogGestioneEdicola.open();
                        }
                    }
                }
            }
        });

        this.annulla.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                AnagraficaRiviste.this.textFieldBarCode.clear();
                AnagraficaRiviste.this.textFieldDescrizione.clear();
                AnagraficaRiviste.this.numberFieldPrezzo.clear();
                AnagraficaRiviste.this.comboBoxPeriodo.clear();
                AnagraficaRiviste.this.enableField(false);
            }
        });

        this.southContainer.add(conferma,annulla);
        this.southContainer.setMinWidth(50,Unit.PERCENTAGE);
        this.southContainer.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        this.container.add(this.northContainer,this.centralContainer,this.southContainer);

        return this.container;
    }

    @Override
    public Component buildContent() {
        this.enableField(true);
        return this.container;
    }

    public void enableField(boolean enable) {
        this.textFieldBarCode.setEnabled(enable);
        this.textFieldDescrizione.setEnabled(enable);
        this.numberFieldPrezzo.setEnabled(enable);
        this.comboBoxPeriodo.setEnabled(enable);
        this.conferma.setEnabled(enable);
        this.annulla.setEnabled(enable);
    }

    public TextField getTextFieldBarCode() {
        return textFieldBarCode;
    }

    public TextField getTextFieldDescrizione() {
        return textFieldDescrizione;
    }

    public NumberField getNumberFieldPrezzo() {
        return numberFieldPrezzo;
    }

    public ComboBox<Periodo> getComboBoxPeriodo() {
        return comboBoxPeriodo;
    }

    public Button getConferma() {
        return conferma;
    }

    public Button getAnnulla() {
        return annulla;
    }

    public Binder<Rivista> getBinder() {
        return binder;
    }

    public Binder<Rivista> getBinderBarcode() {
        return binderBarcode;
    }
}
