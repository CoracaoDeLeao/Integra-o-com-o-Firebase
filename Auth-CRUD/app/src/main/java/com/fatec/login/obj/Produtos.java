package com.fatec.login.obj;

import java.util.HashMap;
import java.util.Map;

public class Produtos {

    private int ID;
    private float preco;

    public Produtos() {}

    public Produtos(float preco, int ID) {
        this.preco = preco;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Map<String, Object> toMapID() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("id", ID);
        return obj;
    }

    public Map<String, Object> toMapPreco() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("preco", preco);
        return obj;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("id", ID);
        obj.put("preco", preco);
        return obj;
    }
}
