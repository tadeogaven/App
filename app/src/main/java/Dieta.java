import com.example.healthme.Dificultad;

public enum Dieta {
    VEGETARIANA, VEGANA, CARNIVORA;

    public int getId(){
        if(this == VEGETARIANA)
            return 1;
        if(this == VEGANA)
            return 2;
        if(this == CARNIVORA)
            return 3;
        return 0;
    }

    public static Dieta fromString(String s){
        if(s == "Vegetariana"){
            return VEGETARIANA;
        }
        if(s == "Vegana"){
            return VEGANA;
        }
        if(s == "Carnivora"){
            return CARNIVORA;
        }

        return VEGETARIANA;
    }
}
