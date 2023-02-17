package it.fornaro.gestione_edicola.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.data.selection.SelectionListener;
import com.vaadin.flow.data.value.ValueChangeMode;
import it.fornaro.gestione_edicola.model.Rivista;
import it.fornaro.gestione_edicola.service.RivistaService;

import java.util.List;

public class TabellaRivista extends ContainerTabArchivio {

    private ContainerMenuToolTipTabArchivio northContainer;
    private Grid<Rivista> grid;

    public TabellaRivista(RivistaService rivistaService,TabsEdicola tabsEdicola) {
        super(tabsEdicola,rivistaService);
    }

    @Override
    protected Component initContent() {
        this.northContainer = new ContainerMenuToolTipTabArchivio(tabsEdicola);
        this.northContainer.getContent();

        this.grid = new Grid<>();
        this.grid.addColumn(Rivista::getBarCode).setSortable(true).setHeader("Barcode").setAutoWidth(true);
        this.grid.addColumn(Rivista::getDescrizione).setSortable(true).setHeader("Nome").setAutoWidth(true);
        this.grid.addColumn(Rivista::getPrezzo).setSortable(true).setHeader("Prezzo").setAutoWidth(true);
        this.grid.addColumn(Rivista::getPeriodo).setSortable(true).setHeader("Periodo").setAutoWidth(true);

        this.grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        this.grid.addSelectionListener(new SelectionListener<Grid<Rivista>, Rivista>() {
            @Override
            public void selectionChange(SelectionEvent<Grid<Rivista>, Rivista> event) {
                Rivista rivistaSelected = grid.asSingleSelect().getValue();
                northContainer.setRivistaToMenuBarToolTip(rivistaSelected);
            }
        });

        List<Rivista> riviste = this.rivistaService.findAll();

        GridListDataView<Rivista> dataView = this.grid.setItems(riviste);

        TextField searchField = new TextField();
        searchField.setWidth("50%");
        searchField.setPlaceholder("Search");
        searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        searchField.setValueChangeMode(ValueChangeMode.EAGER);
        searchField.addValueChangeListener(e -> dataView.refreshAll());

        dataView.addFilter(rivista -> {
            String searchTerm = searchField.getValue().trim();

            if (searchTerm.isEmpty())
                return true;

            boolean matchesBarcode = matchesTerm(rivista.getBarCode(),
                    searchTerm);
            boolean matchesDescrizione = matchesTerm(rivista.getDescrizione(), searchTerm);

            return matchesBarcode || matchesDescrizione;
        });

        this.container.add(this.northContainer,searchField,this.grid);

        return this.container;
    }

    private boolean matchesTerm(String property, String searchTerm) {
        return property.contains(searchTerm);
    }
}
