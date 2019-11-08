package com.example.healthme;

public enum Dificultad {
    FACIL, MEDIO, DIFICIL, MUY_DIFICIL;

    public int getId(){
        if(this == FACIL)
            return 1;
        if(this == MEDIO)
            return 2;
        if(this == DIFICIL)
            return 3;
        if(this == MUY_DIFICIL)
            return 4;
        return 0;
    }

    public static Dificultad fromString(String s){
        if(s == "Fácil"){
            return FACIL;
        }
        if(s == "Medio"){
            return MEDIO;
        }
        if(s == "Difícil"){
            return DIFICIL;
        }
        if(s == "Muy Difícil"){
            return MUY_DIFICIL;
        }
        return FACIL;
    }
}
