package com.example.jpa.util.paginator;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PageRender<T> {

    private String url;
    private Page<T> page;

    private Integer totalPaginas;
    private Integer numElementosPorPagina;
    private Integer paginaActual;

    private List<PageItem> paginas;

    public PageRender(String url, Page<T> page){
        this.url = url;
        this.page = page;
        paginas = new ArrayList<>();

        numElementosPorPagina = page.getSize();
        totalPaginas = page.getTotalPages();
        paginaActual = page.getNumber() + 1;

        Integer desde, hasta;
        if(totalPaginas <= numElementosPorPagina){
            desde = 1;
            hasta = totalPaginas;
        }else{
            if(totalPaginas <= numElementosPorPagina/2){
                desde = 1;
                hasta = numElementosPorPagina;
            } else if (paginaActual >= totalPaginas - numElementosPorPagina/2) {
                desde = totalPaginas - numElementosPorPagina +1;
                hasta = numElementosPorPagina;
            }else{
                desde = paginaActual - numElementosPorPagina/2;
                hasta = numElementosPorPagina;
            }
        }

        for (int i = 0; i < hasta; i++) {
            paginas.add(new PageItem(desde+i,paginaActual == desde+1))
        }
    }
}
