package com.example.culturallis.Model.Entity;

import java.io.Serializable;
import java.util.List;

public class CourseList implements Serializable {
    private List<CourseCard> lista;

    public CourseList(List<CourseCard> lista) {
        this.lista = lista;
    }

    public List<CourseCard> getLista() {
        return lista;
    }
}
