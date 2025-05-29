import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Game {

    private String name;
    private String category;
    private int price;
    private int quality;

    Game(String name, String category, int price, int quality) {    //constructor
        this.name = name;
        this.category = category;
        this.price = price;
        this.quality = quality;
    }
    public String getName() {return name;}
    public String getCategory() {return category;}
    public int getPrice() {return price;}
    public int getQuality() {return quality;}

}

class Dataset{                          //constructor
    private ArrayList<Game> data;
    public String sortedByAttribute;

    Dataset(ArrayList<Game> data) {
        data = data;
    }

    ArrayList<Game> getGamesByPrice(int price){
        ArrayList<Game> resultado = new ArrayList<>();

        //si ya esta ordenado por precio usar binarysearch

        if(sortedByAttribute.equals("price")){

            int L=0;
            int R= data.size()-1;

            while (L<=R){
                int mid = L + (R-L)/2;
                Game gamemedio = data.get(mid);
                if(gamemedio.getPrice() == price){
                    int i=mid;
                    while (i >= 0 && data.get(i).getPrice() == price) {
                        resultado.add(data.get(i));
                        i--;
                    }
                    i=mid+1;
                    while (i < data.size() && data.get(i).getPrice() == price) {
                        resultado.add(data.get(i));
                        i++;
                    }
                    return resultado;
                } else if (gamemedio.getPrice() < price) {
                    L=mid+1;
                }else {
                    R=mid-1;
                }

            }
            return resultado;
        }

        //si no esta ordenado por precio desde el inicio, se usa busqueda lineal
        for (Game game: data) {
            if(game.getPrice() == price){
                resultado.add(game);
            }
        }
        return resultado;
    }


    ArrayList<Game> getGamesByPriceRange(int lowerPrice, int higherP){

        ArrayList<Game> resultado = new ArrayList<>();
        if(!sortedByAttribute.equals("price")){
            for (Game game: data) {
                int price = game.getPrice();
                if(price >= lowerPrice && price <= higherP){
                    resultado.add(game);
                }
            }
            return resultado;
        }
        int L=0;
        int R= data.size()-1;
        int indice=-1;

        while (L<=R){
            int mid = L + (R-L)/2;
            if(data.get(mid).getPrice() == lowerPrice){
                indice=mid;
                R=mid-1;
            } else {
                L=mid+1;
            }
        }
        if(indice == -1){
            return resultado;
        }
        for(int i=indice;i<data.size();i++){
            int price = data.get(i).getPrice();
            if (price > higherP){
                break;
            }
            resultado.add(data.get(i));
        }
        return resultado;
    }


    ArrayList<Game> getGamesByCategory(String category){
        ArrayList<Game> resultado = new ArrayList<>();

        if(!sortedByAttribute.equals("category")){
            for (Game game: data) {
                if(game.getCategory().equals(category)){
                    resultado.add(game);
                }
            }
            return resultado;
        }

        int L=0;
        int R= data.size()-1;
        int indice=-1;

        while (L<=R){
            int mid = L + (R-L)/2;
            String categorymid = data.get(mid).getCategory();
            int compare=categorymid.compareTo(category);
            if(compare==0){
                indice=mid;
                break;
            }else if(compare<0){
                L=mid+1;
            }else {
                R=mid-1;
            }

        }
        if(indice != -1){
            int i=indice;
            while (i >= 0 && data.get(i).getCategory().equals(category)){
                resultado.add(data.get(i));
                i--;
            }
            i=indice+1;
            while (i < data.size() && data.get(i).getCategory().equals(category)){
                resultado.add(data.get(i));
                i++;
            }

        }
        return resultado;
    }


    ArrayList<Game> getGamesByQuality(int quality){
        ArrayList<Game> resultado = new ArrayList<>();
        if(!sortedByAttribute.equals("quality")){
            for (Game game: data) {
                if(game.getQuality() == quality){
                    resultado.add(game);
                }
            }
            return resultado;
        }
        int L=0;
        int R= data.size()-1;
        int indice=-1;
        while (L<=R){
            int mid = L + (R-L)/2;
            int qualitymid = data.get(mid).getQuality();
            if(qualitymid == quality){
                indice=mid;
                break;
            }
            else if(qualitymid<quality){
                L=mid+1;
            }
            else {
                R=mid-1;
            }
        }
        if(indice != -1){
            int i=indice;
            while (i >= 0 && data.get(i).getQuality() == quality){
                resultado.add(data.get(i));
                i--;
            }
            i=indice+1;
            while (i < data.size() && data.get(i).getQuality() == quality){
                resultado.add(data.get(i));
                i++;
            }
        }
        return resultado;
    }

    private Comparator<Game> comparador (String attribute) {//metodo auxiliar para saber por que atributo comparar
        switch (attribute){
            case "price":
                return Comparator.comparingInt(Game::getPrice);
            case "category":
                return Comparator.comparing(Game::getCategory);
            case "quality":
                return Comparator.comparingInt(Game::getQuality);
            default:
                return Comparator.comparing(Game::getPrice);
        }
    }

    void sortByAlgorithm(String algorithm, String attribute){
        Comparator<Game> comparador = comparador(attribute);
        switch (algorithm){
            case "bubbleSort":
                bubbleSort(data, comparador);
                break;
            case "insertionSort":
                insertionSort(data,comparador);
                break;
            case "selectionSort":
                selectionSort(data,comparador);
                break;
            case "mergeSort":
                data=mergeSort(data,comparador);
                break;
            case "quickSort":
                quickSort(data, 0, data.size()-1,comparador);
                break;
            default:
                Collections.sort(data, comparador);
        }
        if (attribute.equals("price") || attribute.equals("category") || attribute.equals("quality")) {
            sortedByAttribute = attribute;
        } else sortedByAttribute = "price";
    }
    private void bubbleSort(ArrayList<Game> data,Comparator<Game> comparador){
        int n=data.size();
        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-1-i;j++){
                Game indice=data.get(j);
                Game next=data.get(j+1);
                if(comparador.compare(indice,next)>0){
                    data.set(j,next);
                    data.set(j+1,indice);
                }
            }
        }

    }
    private void insertionSort(ArrayList<Game> data,Comparator<Game> comparador){
        int n=data.size();
        for(int i=1;i<n;i++){
            Game indice=data.get(i);
            int j=i-1;
            while(j>=0 && comparador.compare(data.get(j),indice)>0){
                data.set(j+1,data.get(j));
                j--;
            }
            data.set(j+1,indice);
        }
    }
    private void selectionSort(ArrayList<Game> data,Comparator<Game> comparador){
        int n=data.size();
        for(int i=0;i<n-1;i++){
            int min=i;
            for(int j=i+1;j<n;j++){
                if(comparador.compare(data.get(j),data.get(min))>0){
                    min=j;
                }
            }
            if (min!=i){
                Game aux = data.get(i);
                data.set(i,data.get(min));
                data.set(min,aux);
            }
        }
    }
    private ArrayList<Game> mergeSort(ArrayList<Game> data, Comparator<Game> comparador){
        if(data.size()<=1){
            return data;
        }
        int mid=data.size()/2;
        ArrayList<Game> L = new ArrayList<>(data.subList(0,mid));
        ArrayList<Game> R = new ArrayList<>(data.subList(mid,data.size()));
        L=mergeSort(L,comparador);
        R=mergeSort(R,comparador);
        ArrayList<Game> resultado = new ArrayList<>();
        int i=0;
        int j=0;
        while(i<L.size()&&j<R.size()){
            if(comparador.compare(L.get(i),R.get(j))<0){
                resultado.add(L.get(i));
                i++;
            }
            else {
                resultado.add(R.get(j));
                j++;
            }
        }
        while(i<L.size()){
            resultado.add(L.get(i));
            i++;
        }
        while(j<R.size()){
            resultado.add(R.get(j));
            j++;
        }
        return resultado;
    }
    private void quickSort(ArrayList<Game> data, int min, int max, Comparator<Game> comparador){
        if(min < max){
            Game pivot = data.get(max);
            int i=min-1;
            for(int j=min;j<=max;j++){
                if(comparador.compare(data.get(j),pivot)<0){
                    i++;
                    Game temp = data.get(j);
                    data.set(i,data.get(j));
                    data.set(j,temp);
                }
            }
            Game temp=data.get(i+1);
            data.set(i+1,data.get(max));
            data.set(max,temp);
            int k=i+1;
            quickSort(data,min,k-1,comparador);
            quickSort(data,k+1,max,comparador);
        }
    }

}