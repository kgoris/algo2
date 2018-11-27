import java.util.*;

public class Main {
    /*
    On considère ici qu'un trajet est un déplacement de case
     */
    public static int countTrajets(int n, Integer x, Integer y, int cpt){
        if(n == 0){
            return 0;
        }else if(n==1){
            return 0;
        }else if(n==2){
            return 3;
        }else {
            if(x+1 == n-1 && y+1 == n-1){
                return cpt;
            }
            if(x+1 <=n-1){

                cpt = countTrajets(n, x+1, y, ++cpt);
            }
            if(y+1<=n-1){

                cpt = countTrajets(n, x, y + 1, ++cpt);
            }
            if(x+1 <= n-1 && y+1 <= n-1){

                cpt = countTrajets(n, x+1, y+1, ++cpt);
            }
            return cpt;
        }

    }
    static class Coordonnees{
        int x;
        int y;
        int value;
        public Coordonnees(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Coordonnees{" +
                    "x=" + x +
                    ", y=" + y +
                    ", value=" + value +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordonnees that = (Coordonnees) o;
            return x == that.x &&
                    y == that.y &&
                    value == that.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, value);
        }
    }

    public static Map<Coordonnees, Integer> cheminMap = new HashMap<>();

    /*
    Cet algorithme applique bien la programmation dynamique ascendante. D'après mes tests, il permet de trouver le
    chemin permettant d'avoir le plus de points. Il trouve le plus de point. Cependant, je n'ai pas eu le temps d'optimiser
    la solution pour qu'il retienne concrètement le chemin. Il faudrait le faire avec un array parce que la liste, comme elle
    est référencée à l'extérieur de la méthode, retient toutes les positions parcourues.
     */
    public static int findBestTrajetsWithList(int[][] echiquier, int n, int x, int y, Integer cpt, List<Coordonnees> coordonneesList){

        cpt += echiquier[y][x];
        Coordonnees coordonnees = new Coordonnees(x, y, cpt);
        coordonneesList.add(coordonnees);
        if (cheminMap.containsKey(coordonnees)) {
            return cheminMap.get(coordonnees);
        }


        if(x == n-1 && y == n-1){
            cheminMap.put(coordonnees,cpt);
            return cpt;
        }else {
            if(x+1 <= n-1 && y+1 <= n-1){
                int maxDiag = findBestTrajetsWithList(echiquier, n, x+1, y+1, cpt, coordonneesList);
                int maxDroite = findBestTrajetsWithList(echiquier, n, x+1, y, cpt, coordonneesList);
                int maxHaut = findBestTrajetsWithList(echiquier, n, x, y+1, cpt, coordonneesList);
                int maxOfMax = Math.max(maxDiag, Math.max(maxDroite, maxHaut));

                cheminMap.put(coordonnees, maxOfMax);
                return maxOfMax;
            }else if(x+1 >=n-1 && y+1 <=n-1){
                cpt = findBestTrajetsWithList(echiquier, n, x, y+1, cpt, coordonneesList);
                cheminMap.put(coordonnees, cpt);
                return cpt;
            }else if(y+1>=n-1 && x+1 <=n-1){
                cpt = findBestTrajetsWithList(echiquier, n, x + 1, y , cpt, coordonneesList);
                cheminMap.put(coordonnees, cpt);
                return cpt;
            }else{
                return 0;
            }
        }
    }
    public static void main(String[] args) {
        int[][] echiquier = {{0, 8,1,9,4},{25,3,2,5,42},{18,38,8,2,1},{42,5,84,34,27},{2,5,8,1,54}};
        int res = countTrajets(5, 0, 0, 0);
        System.out.println(String.format("Resultat1: %d", res));
        cheminMap = new HashMap<>();
        System.out.println("CheminList:");
        List<Coordonnees> cheminList = new ArrayList<>();
        Integer cpt = 0;
        findBestTrajetsWithList(echiquier, 5, 0, 0, cpt,cheminList);
        for(Coordonnees coordonnees: cheminList){
            System.out.println(String.format("%d %d %d", coordonnees.x, coordonnees.y, coordonnees.value));
        }
    }
}
