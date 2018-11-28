/*TP5*/

public class Main {
    /*
        EXERCICE 2
        Compréhension de l'énoncé: le tableau contient des valeurs allant
        de 0 à n-1.
        Complexité de log n on divise le tableau en deux et on fait la récursion sur les
        deux parties du tableau. Le travail effectué dans la condition d'arrêt est en O(1)
     */
    public static Integer[] tempArray;
    public static Integer findDoublon(Integer[] array, int debut, int fin){
        if(debut == fin){
            //O(1)
            if(tempArray[array[debut]] != null) {
                return array[debut];
            }else {
                tempArray[array[debut]] = array[debut];
                return null;
            }
        }else{
            int milieu = (debut+fin) / 2;
            //O(log n)
            Integer res = findDoublon(array, debut, milieu);
            Integer res2 = findDoublon(array, milieu + 1, fin);

            //O(1)
            if(res != null){
                return res;
            }else if(res2 != null){
                return res2;
            }else {
                return null;
            }
        }
    }

    public static Integer[] minMax(Integer[] array, int debut, int fin) {

        if (debut == fin) {
            System.out.println("comparaison");
            Integer[] resMinMax = {array[debut], array[debut]};
            return resMinMax;
        } else {
            int milieu = (debut + fin) / 2;
            //O(log n)
            Integer[] res = minMax(array, debut, milieu);
            Integer[] res2 = minMax(array, milieu + 1, fin);
            Integer[] resMinMax = new Integer[2];
            if (res[0] < res2[0]) {
                System.out.println("comparaison");
                resMinMax[0] = res[0];
            } else {
                resMinMax[0] = res2[0];
            }
            if (res[1] > res2[1]) {
                System.out.println("comparaison");
                resMinMax[1] = res[1];
            } else {
                resMinMax[1] = res2[1];
            }
            return resMinMax;
        }
    }
    /*
    On considère que n est compris entre 0 et la taille de l'array le plus grand
    entre les deux array
     */
    public static Integer rangN(Integer[] array1, Integer[] array2, int n, int debut, int fin){

    }

    public static void main(String[] args) {
        Integer[] exempleArray = {0, 1, 2, 3, 3, 4, 5, 6, 7, 8};
        tempArray = new Integer[exempleArray.length];
        Integer res = findDoublon(exempleArray, 0, exempleArray.length -1);
        System.out.println("exercice2: Res " + res);

        Integer[] minMaxArray = {3, 9, 5, 0, 12, 14, 1, 2};
        Integer[] resMinMax = minMax(minMaxArray, 0, minMaxArray.length -1);
        System.out.println(String.format("exercice3: min %d max %d", resMinMax[0], resMinMax[1]));

        Integer[] array1 = {-4, -1, 5, 6, 12, 32, 65, 75, 88, 90};
        Integer[] array2 = {0, 4, 7, 8, 9, 13, 14, 17};
        Integer nieme = rangN(array1, array2, 9, 0, array1.length + array2.length);
        System.out.println(String.format("exercice6: min %d max %d", resMinMax[0], resMinMax[1]));
    }
}
