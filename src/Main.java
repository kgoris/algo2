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

    /*
    EXERCICE 4
     */
    public static Integer[] minMax(Integer[] array, int debut, int fin) {

        if (debut == fin) {
            Integer[] resMinMax = {array[debut], array[debut]};
            return resMinMax;
        } else {
            int milieu = (debut + fin) / 2;
            //O(log n)
            Integer[] res = minMax(array, debut, milieu);
            Integer[] res2 = minMax(array, milieu + 1, fin);
            Integer[] resMinMax = new Integer[2];
            if (res[0] < res2[0]) {
                resMinMax[0] = res[0];
            } else {
                resMinMax[0] = res2[0];
            }
            if (res[1] > res2[1]) {
                resMinMax[1] = res[1];
            } else {
                resMinMax[1] = res2[1];
            }
            return resMinMax;
        }
    }
    /*
    EXERCICE 7
    Complexité n log n
     */
    public static Integer maxSum(Integer[] array, int debut, int fin){
        if(debut == fin){
            return array[debut];
        }

        //log n
        Integer res1 = maxSum(array, debut, debut );
        Integer res2 = null;
        if(debut < fin) {
            res2 = maxSum(array, debut + 1, fin);
        }

        Integer maxRes = null;
        if(res1 < res2){
            maxRes = res2;
        }else{
            maxRes = res1;
        }

        int sum = 0;
        int tmp = 0;
        //pire cas: on parcourt n-1 éléments -> O(n)
        for(int i = debut ; i<fin; ++i){
            tmp+= array[i];
            if(sum < tmp){
                sum = tmp;
            }
        }
        if(maxRes < sum){
            return sum;
        }else{
            return maxRes;
        }
    }

    public static void main(String[] args) {
        Integer[] exempleArray = {0, 1, 2, 3, 3, 4, 5, 6, 7, 8};
        tempArray = new Integer[exempleArray.length];
        Integer res = findDoublon(exempleArray, 0, exempleArray.length -1);
        System.out.println("exercice2: Res " + res);

        Integer[] minMaxArray = {3, 9, 5, 0, 12, 14, 1, 2};
        Integer[] resMinMax = minMax(minMaxArray, 0, minMaxArray.length -1);
        System.out.println(String.format("exercice4: min %d max %d", resMinMax[0], resMinMax[1]));

        Integer[] array1 = {3, -4, 6, 2, -5, 9, 8, -9, -2, 8};
        int max = maxSum(array1, 0, array1.length - 1);
        System.out.println(String.format("exercice7: max %d", max));
    }
}
