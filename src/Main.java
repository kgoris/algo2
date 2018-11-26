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
    public static int findBestTrajets(int[][] echiquier, int n, int x, int y, int cpt, Coordonnees[] coordonneesVect, int nextCoord){

        cpt += echiquier[y][x];
        Coordonnees coordonnees = new Coordonnees(x, y, cpt);
/*        if (cheminMap.containsKey(coordonnees)) {
            return cheminMap.get(coordonnees);
        }*/
        coordonneesVect[nextCoord] = coordonnees;
        ++nextCoord;
        if(x == n-1 && y == n-1){
            cheminMap.put(coordonnees,cpt);
            return cpt;
        }else {
            if(x+1 <= n-1 && y+1 <= n-1){
                int maxDiag = findBestTrajets(echiquier, n, x+1, y+1, cpt, coordonneesVect, nextCoord);
                int maxDroite = findBestTrajets(echiquier, n, x+1, y, cpt, coordonneesVect, nextCoord);
                int maxHaut = findBestTrajets(echiquier, n, x, y+1, cpt, coordonneesVect, nextCoord);
                int maxOfMax = Math.max(maxDiag, Math.max(maxDroite, maxHaut));
                cheminMap.put(coordonnees, maxOfMax);
                return maxOfMax;
                /*return Math.max(findBestTrajets(echiquier, n, x+1, y+1, cpt, coordonnees, nextCoord),
                        Math.max(findBestTrajets(echiquier, n, x+1, y, cpt, coordonnees, nextCoord), );*/
            }else if(x+1 >=n-1 && y+1 <=n-1){
                cpt = findBestTrajets(echiquier, n, x, y+1, cpt, coordonneesVect, nextCoord);
                cheminMap.put(coordonnees, cpt);
                return cpt;
            }else if(y+1>=n-1 && x+1 <=n-1){
                cpt = findBestTrajets(echiquier, n, x + 1, y , cpt, coordonneesVect, nextCoord);
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
        Coordonnees[] chemin = new Coordonnees[echiquier.length * echiquier.length];
        String[] test = new String[4];
        int res2 = findBestTrajets(echiquier, 5, 0, 0, 0,chemin , 0);
        System.out.println(String.format("Resultat2: %d", res2));
        System.out.println("Chemin:");
        for(int i=0; i<chemin.length ; ++i){
            if(chemin[i] == null){
                break;
            }
            Coordonnees coordonnees = chemin[i];
            System.out.println(String.format("%d %d %d", coordonnees.x, coordonnees.y, coordonnees.value));
        }
    }
}
