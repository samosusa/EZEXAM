package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model {
    private Converter converter;

    public ModelManager() {
        this.converter = new Converter();

    }

    @Override
    public
    synchronized String convert(String source) {
        if (converter.toUpperCase(source) != null) {
            String reply = converter.toUpperCase(source);
            return reply;
        }

        throw new IllegalArgumentException("Empty Field");
    }



    }
