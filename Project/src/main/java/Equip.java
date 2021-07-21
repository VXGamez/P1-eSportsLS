import java.util.*;

public class Equip{

    private String team;
    private float winrate;
    private List<Jugador> jugadores;

    public Equip() {
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public float getWinrate() {
        return winrate;
    }

    public void setWinrate(float winrate) {
        this.winrate = winrate;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public static void quickSortEquip(Equip[] arr, int start, int end){

        int partition = partition(arr, start, end);

        if(partition-1>start) {
            quickSortEquip(arr, start, partition - 1);
        }
        if(partition+1<end) {
            quickSortEquip(arr, partition + 1, end);
        }

    }

    public static void quickSortJugador(List<Jugador> j, int start, int end, String criterio){
        int partition = 0;

        if(criterio.equals("2")){
            partition = partitionNationality(j, start, end);
        }else if(criterio.equals("3")){

            partition = partitionKda(j, start, end);

        }

        if(partition-1>start) {
            quickSortJugador(j, start, partition - 1, criterio);
        }
        if(partition+1<end) {
            quickSortJugador(j, partition + 1, end, criterio);
        }

    }

    private static int partitionNationality(List<Jugador> arr, int start, int end){
        Jugador pivot = arr.get(end);

        for(int i=start; i<end; i++) {
            int compare = arr.get(i).getNationality().compareTo(pivot.getNationality());
            if (compare < 0) {
                Jugador temp = arr.get(start);
                arr.set(start, arr.get(i));
                arr.set(i, temp);
                start++;
            }else if(compare == 0){
                int compare2 = arr.get(i).getName().toLowerCase().compareTo(pivot.getName().toLowerCase());
                if (compare2 < 0){
                    Jugador temp = arr.get(start);
                    arr.set(start, arr.get(i));
                    arr.set(i, temp);
                    start++;
                }
            }
        }

        Jugador temp = arr.get(start);
        arr.set(start, pivot);
        arr.set(end, temp);

        return start;

    }

    private static double escalaU(double num, double min, double max) {
        return 1 - ((num - min) / (max - min));
    }

    private static int partitionKda(List<Jugador> arr, int start, int end){
        Jugador pivot = arr.get(end);
        Jugador[] j = new Jugador[arr.size()];
        Equip[] ar = new Equip[0];
        for(int i=start; i<end; i++){
            int compare = arr.get(i).getNationality().compareTo(pivot.getNationality());
            if (compare < 0) {
                Jugador temp = arr.get(start);
                arr.set(start, arr.get(i));
                arr.set(i, temp);
                start++;
            }else if(compare == 0 ){
                double pes1 = escalaU(arr.get(i).getWinrate(), 0, trobaMaxValWR(ar, arr.toArray(j), 2))*0.6 + 0.4*escalaU(arr.get(i).getKda(), 0, trobaMaxValWR(ar, arr.toArray(j), 3));
                double pes2 = escalaU(pivot.getWinrate(), 0, trobaMaxValWR(ar, arr.toArray(j), 2))*0.6 + 0.4*escalaU(pivot.getKda(), 0, trobaMaxValWR(ar, arr.toArray(j), 3));
                if(pes1 < pes2){
                    Jugador temp = arr.get(start);
                    arr.set(start, arr.get(i));
                    arr.set(i, temp);
                    start++;
                }else if(arr.get(i).getWinrate() == pivot.getWinrate()){
                    if(arr.get(i).getKda() > pivot.getKda()){
                        Jugador temp = arr.get(start);
                        arr.set(start, arr.get(i));
                        arr.set(i, temp);
                        start++;
                    }
                }
            }
        }

        Jugador temp = arr.get(start);
        arr.set(start, pivot);
        arr.set(end, temp);
        return start;
    }

    private static int partition(Equip[] arr, int start, int end){
        Equip pivot = arr[end];
        for(int i=start; i<end; i++){
            if(arr[i].getWinrate() > pivot.getWinrate()){
                Equip temp = arr[start];
                arr[start] = arr[i];
                arr[i] = temp;
                start++;
            }
        }
        Equip temp = arr[start];
        arr[start] = pivot;
        arr[end] = temp;
        return start;
    }

    public void sortEquip(Equip[] arr, int l, int m, int r){
        int n1 = m - l + 1;
        int n2 = r - m;
        Equip[] L = new Equip [n1];
        Equip[] R= new Equip [n2];
        for (int i=0; i<n1; ++i) {
            L[i] = arr[l + i];
        }
        for (int j=0; j<n2; ++j) {
            R[j] = arr[m + 1 + j];
        }
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2){
            if (L[i].getWinrate() >= R[j].getWinrate()){
                arr[k] = L[i];
                i++;
            }else{
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1){
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2){
            arr[k] = R[j];
            j++;
            k++;
        }
    }


    public static void sortJugador(List<Jugador> alist, List<Jugador> blist, List<Jugador> list, String criterio, double maxWR, double maxKDA) {
        if (criterio.equals("2")) {
            int alistIndex = 0, blistIndex = 0, listIndex = 0;
            while (alistIndex < alist.size() && blistIndex < blist.size()) {
                int compare = alist.get(alistIndex).getNationality().compareTo(blist.get(blistIndex).getNationality());
                if (compare == 0) {
                    int compare2 = alist.get(alistIndex).getName().toLowerCase().compareTo(blist.get(blistIndex).getName().toLowerCase());
                    if (compare2 < 0) {
                        list.set(listIndex, alist.get(alistIndex));
                        alistIndex++;
                    }else{
                        list.set(listIndex, blist.get(blistIndex));
                        blistIndex++;
                    }
                }else{
                    if (compare < 0) {
                        list.set(listIndex, alist.get(alistIndex));
                        alistIndex++;
                    }else{
                        list.set(listIndex, blist.get(blistIndex));
                        blistIndex++;
                    }
                }
                listIndex++;
            }

            while (alistIndex < alist.size()) {
            list.set(listIndex, alist.get(alistIndex));
            alistIndex++;
            listIndex++;
            }

            while (blistIndex < blist.size()) {
            list.set(listIndex, blist.get(blistIndex));
            blistIndex++;
            listIndex++;
            }
        } else if (criterio.equals("3")) {
            Jugador[] j = new Jugador[alist.size()];
            Jugador[] jug = new Jugador[blist.size()];
            Equip[] ar = new Equip[0];
            int alistIndex = 0, blistIndex = 0, listIndex = 0;
            while (alistIndex < alist.size() && blistIndex < blist.size()) {
                int compare = alist.get(alistIndex).getNationality().compareTo(blist.get(blistIndex).getNationality());
                if (compare == 0) {
                    double pes1 = escalaU(alist.get(alistIndex).getWinrate(), 0, maxWR)*0.6 + 0.4*escalaU(alist.get(alistIndex).getKda(), 0, maxKDA);
                    double pes2 = escalaU(blist.get(blistIndex).getWinrate(), 0, maxWR)*0.6 + 0.4*escalaU(blist.get(blistIndex).getKda(), 0, maxKDA);

                    if (pes1 < pes2) {
                        list.set(listIndex, alist.get(alistIndex));
                        alistIndex++;
                    } else {
                        list.set(listIndex, blist.get(blistIndex));
                        blistIndex++;
                    }

                }else{
                    if (compare < 0) {
                        list.set(listIndex, alist.get(alistIndex));
                        alistIndex++;
                    }else{
                        list.set(listIndex, blist.get(blistIndex));
                        blistIndex++;
                    }
                }
                listIndex++;
            }

            while (alistIndex < alist.size()) {
                list.set(listIndex, alist.get(alistIndex));
                alistIndex++;
                listIndex++;
            }

            while (blistIndex < blist.size()) {
                list.set(listIndex, blist.get(blistIndex));
                blistIndex++;
                listIndex++;
            }
        }
    }

    public void mergeSortEquip(Equip[] arr, int l, int r) {
        if (l < r) {
            int m = (l+r)/2;

            mergeSortEquip(arr, l, m);
            mergeSortEquip(arr , m+1, r);

            sortEquip(arr, l, m, r);
        }
    }

    public void mergeSortJugador(List<Jugador> list, String criterio, double maxWR, double maxKDA) {

        List<Jugador> alist = new ArrayList<>(list.subList(0, list.size() / 2));
        List<Jugador> blist = new ArrayList<>(list.subList(list.size() / 2, list.size()));

        if (list.size() >1) {
            mergeSortJugador(alist, criterio, maxWR, maxKDA);
            mergeSortJugador(blist, criterio, maxWR, maxKDA);
            sortJugador(alist, blist, list, criterio, maxWR, maxKDA);
        }
    }

    public static int trobaMaxVal2(Equip[] ar){
        int max1=0, max2=0;

        for(int i=0; i<ar.length; i++ ){
            max1=0;
            for(int j=0; j<ar.length ;j++){
                if( Math.round(ar[i].getWinrate()) == Math.round(ar[j].getWinrate())){
                    max1++;
                }
            }
            if(max1>max2){
                max2=max1;
            }
        }

        return max2;
    }

    public static int trobaMaxValWR(Equip[] ar, Jugador[] ar2, int criterio){

        int max=0;
        if(criterio == 1){
          for(int i=0; i<ar.length; i++ ){
              if(ar[i].getWinrate() > max){
                  max = (int) ar[i].getWinrate();
              }
          }
        } else if(criterio == 2){
          for(int i=0; i<ar2.length; i++ ){
              if(ar2[i]!=null && ar2[i].getWinrate() > max){
                  max = (int) ar2[i].getWinrate();
              }
          }
        }else if(criterio == 3){
            for(int i=0; i<ar2.length; i++ ){
                if(ar2[i]!=null && ar2[i].getKda() > max){
                    max = (int) ar2[i].getKda();
                }
            }
        }

        return max;

    }


    private static int countNationality(Jugador[] ar){
        int max1, max2=0;

        for(int i=0; i<ar.length; i++ ){
            max1=0;
            for(int j=0; j<ar.length ;j++){
                if(ar[i].getNationality().equalsIgnoreCase(ar[j].getNationality())){
                    max1++;
                }
            }
            if(max1>max2){
                max2=max1;
            }
        }
        return max2;
    }

    private static int totalNacionalitats(Jugador[] ordena){
        int total = 1;
        String aux;
        boolean existe;
        List<String> nacionality = new ArrayList<>();
        nacionality.add(ordena[0].getNationality());
        for(int i = 0; i < ordena.length; i++){
            existe = false;
            aux = ordena[i].getNationality();
            for(int j = 0; j < total; j++){
                int compare = aux.compareToIgnoreCase(nacionality.get(j));
                if (compare == 0) {
                    existe = true;
                    break;
                }
            }
            if(!existe){
                nacionality.add(aux);
                total++;
            }
        }
        return total;
    }

    public static void bucketSortEquips(Equip[] sequence, int maxValue, int maxValue2) {
        Equip[][] bucket = new Equip[maxValue+1][maxValue2+1];
        int aux;
        int[] cont = new int[maxValue+1];
        for (int i = 0; i < maxValue+1; i++){
            int bi = (int) sequence[i].getWinrate();
            bucket[bi][cont[bi]] = sequence[i];
            cont[bi]++;
        }
        Equip d;
        for (int i = 0; i < maxValue+1; i++){
            for (int j = bucket[i].length - 1; j > 0; j--){
                boolean ordenat = true;
                for (int k = 0; k < j; k++){
                    if(bucket[i][k+1]!= null){
                        if ( bucket[i][k].getWinrate() > bucket[i][k+1].getWinrate()){
                            ordenat = false;
                            d = bucket[i][k + 1];
                            bucket[i][k + 1] = bucket[i][k];
                            bucket[i][k] = d;
                        }
                    }
                }
                if (ordenat)  break;
            }
        }
        aux=0;
        for(int i=maxValue; i>=0; i--){
            for(int j=bucket[i].length - 1; j>=0; j--){
                if(bucket[i][j]!=null){
                    sequence[aux] = bucket[i][j];
                    aux++;
                }
            }
        }

    }

    public static void ompleNacional(String[] arr, Jugador[] ord){
        arr[0] = ord[0].getNationality();
        boolean existe;
        int total = 1;
        for(int i=0; i<ord.length; i++){
            existe = false;
            for(int j=0; j<total ;j++){
                if(arr[j].equalsIgnoreCase(ord[i].getNationality())){
                    existe = true;
                }
            }
            if(!existe){
                arr[total] = ord[i].getNationality();
                total++;
            }
        }
    }

    public static void endrecaNacionalitats(String[] arr){
        String aux;
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length-1; j++){
            int compare = arr[j].compareTo(arr[j+1]);
                if(compare > 0){
                    aux = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = aux;
                }
            }
        }
    }
    public static int casellaNacionality(String[] nacional, Jugador player){
        int casella = 0;
        for(int i=0; i<nacional.length ;i++){
            if(nacional[i].equalsIgnoreCase(player.getNationality())){
                casella = i;
            }
        }
        return casella;
    }

    private static Jugador[][] ordenaNacionalitat(Jugador[] ordena){

        String[] nacional = new String[totalNacionalitats(ordena)];
        Jugador[][] bucket = new Jugador[totalNacionalitats(ordena)][countNationality(ordena)];

        ompleNacional(nacional, ordena);
        endrecaNacionalitats(nacional);
        int[] cont = new int[countNationality(ordena)];

        for (int i = 0; i < ordena.length; i++){
            int bi = casellaNacionality(nacional, ordena[i]);
            bucket[bi][cont[bi]] = ordena[i];
            cont[bi]++;
        }
        return bucket;
    }

    private static void invertirArray(Jugador[] a, int n){
        Jugador[] b = new Jugador[n];
        int j = n;
        for (int i = 0; i < n; i++) {
            if(a[i]!=null){
                b[j - 1] = a[i];
                j = j - 1;
            }
        }
    }


    public static List<Jugador> bucketSortJugador(Equip[] arr, int total_players, String criteri) {
        List<Jugador> player = new ArrayList<>();
        Jugador[] ordena = new Jugador[total_players];
        int aux;
        int m=0;
        for(int i=0; i<arr.length ;i++){
            for(Jugador k  : arr[i].getJugadores()){
                ordena[m] = k;
                m++;
            }
        }
        if(criteri.equals("2")) {
            Jugador[][] bucket = ordenaNacionalitat(ordena);
            Jugador d;
            for (int i = 0; i < totalNacionalitats(ordena); i++){
                for (int j = bucket[i].length - 1; j > 0; j--){
                    boolean ordenat = true;
                    for (int k = 0; k < j; k++){
                        if(bucket[i][k+1]!=null){
                            int compara = bucket[i][k].getNationality().compareToIgnoreCase(bucket[i][k+1].getNationality());
                            if(compara == 0){
                                int compare2 = bucket[i][k].getName().compareToIgnoreCase(bucket[i][k+1].getName());
                                if (compare2 > 0){
                                    ordenat = false;
                                    d = bucket[i][k + 1];
                                    bucket[i][k + 1] = bucket[i][k];
                                    bucket[i][k] = d;
                                }
                            }
                        }
                    }
                    if (ordenat)  break;
                }
            }
            aux=0;
            for(int i=0; i<totalNacionalitats(ordena); i++){
                for(int j=0; j<bucket[i].length; j++){
                    if(bucket[i][j]!=null){
                        ordena[aux] = bucket[i][j];
                        aux++;
                    }
                }
            }

            Collections.addAll(player, ordena);

        }else if(criteri.equals("3")){
            Equip[] ar = new Equip[0];
            Jugador[][] bucket = ordenaNacionalitat(ordena);
            for(int k=0; k<bucket.length ;k++) {
                for (int j = bucket[k].length - 1; j > 0; j--){
                    boolean ordenat = true;
                    for (m = 0; m < j; m++){
                        if(bucket[k][m+1]!= null){
                            double pes1 = escalaU(bucket[k][m].getWinrate(), 0, trobaMaxValWR(ar, bucket[k], 2))*0.6 + 0.4*escalaU(bucket[k][m].getKda(), 0, trobaMaxValWR(ar, bucket[k], 3));
                            double pes2 = escalaU(bucket[k][m+1].getWinrate(), 0, trobaMaxValWR(ar, bucket[k], 2))*0.6 + 0.4*escalaU(bucket[k][m+1].getKda(), 0, trobaMaxValWR(ar, bucket[k], 3));

                            if ( pes1 > pes2){
                                ordenat = false;
                                Jugador d = bucket[k][m + 1];
                                bucket[k][m + 1] = bucket[k][m];
                                bucket[k][m] = d;
                            }
                        }
                    }
                    if (ordenat)  break;
                }
            }
            aux=0;
            for(int i=0; i<totalNacionalitats(ordena); i++){
                for(int j=0; j<bucket[i].length; j++){
                    if(bucket[i][j]!=null){
                        ordena[aux] = bucket[i][j];
                        aux++;
                    }
                }
            }

            player.addAll(Arrays.asList(ordena));
        }

        return player;
    }

    private static int trobaTotalWR(Jugador[] j){
      List<Float> total = new ArrayList<>();
      int init=0;
        for(int i = 0; i < j.length; i++){
            if(j[i]!=null){
                init = i;
                break;
            }
        }
        total.add(j[init].getWinrate());
        boolean existe;
        for(int i = 0; i < j.length; i++){
            existe = false;
            if(j[i]!=null) {
                for (Float k : total) {
                    if (j[i].getWinrate() == k) {
                        existe = true;
                    }
                }
                if (!existe) {
                    total.add(j[i].getWinrate());
                }
            }
        }
        return total.size();
    }

    private static void countSort(List<Equip> arr, int n, int exp) {
        Equip[] ord = new Equip[n];
        int i;
        int[] casellas = new int[arr.size()-1];
        Arrays.fill(casellas, 0);

        for (i = 0; i < n; i++)
            casellas[(int)(arr.get(i).getWinrate() / exp) % 10]++;

        for (i = 1; i < 10; i++)
            casellas[i] += casellas[i - 1];

        for (i = n - 1; i >= 0; i--) {
            ord[casellas[(int)(arr.get(i).getWinrate() / exp) % 10] - 1] = arr.get(i);
            casellas[(int)(arr.get(i).getWinrate() / exp) % 10]--;
        }

        for (i = 0; i < n; i++)
            arr.set(i,ord[i]);
    }

    private static int maxDec(Equip[] arr, Jugador[] arr2, int criterio){
			int max=0;
			int partNum = 0;
			String numero;
			int partDecimal=0;
			if(criterio==1){
				for(int i=0; i<arr.length ;i++){
                    numero = Float.toString(arr[i].getWinrate());
                    partNum = numero.indexOf('.');
                    partDecimal = numero.length() - partNum - 1;
				}
				if(partDecimal>max){
					max = partDecimal;
				}
			}else if(criterio == 2){
				for(int i=0; i<arr2.length ;i++){
				    if(arr2[i]!=null){
                        numero = Float.toString(arr2[i].getWinrate());
                        partNum = numero.indexOf('.');
                        partDecimal = numero.length() - partNum - 1;
                    }
				}
				if(partDecimal>max){
					max = partDecimal;
				}
			}else if(criterio == 3){
                for(int i=0; i<arr2.length ;i++){
                    if(arr2[i]!=null){
                        numero = Float.toString(arr2[i].getKda());
                        partNum = numero.indexOf('.');
                        partDecimal = numero.length() - partNum - 1;
                    }
                }
                if(partDecimal>max){
                    max = partDecimal;
                }
            }
			return max;
    }

    public static void radixSortEquips(Equip[] arr) {
        int cont = 0;
        Jugador[] p = new Jugador[1];
        int m = (int) (Math.log10(trobaMaxValWR(arr, p, 1)) + 1);
        double maxDec = Math.pow(10, maxDec(arr, p, 1));

        List<Equip> winrates = new ArrayList<>();
        for(Equip equip:  arr){
            equip.setWinrate((float)(equip.getWinrate()*maxDec));
            winrates.add(cont, equip);
            cont++;
        }

        for (int exp = 1; (m+maxDec)/exp > 0; exp *= 10) {
            countSort(winrates, cont, exp);
        }

        Collections.reverse(winrates);

        for (int i=0; i<winrates.size(); i++){
            winrates.get(i).setWinrate((float) ((float)(double)winrates.get(i).getWinrate()/maxDec));
            arr[i] = winrates.get(i);
        }

    }


    private static void countSortKDA(List<Pesos> arr2, int n, int exp) {
        Pesos[] output = new Pesos[n];
        int i;
        int[] count = new int[arr2.size()-1];
        Arrays.fill(count, 0);

        count = new int[11];
        for (i = 0; i < n; i++) {
            count[(arr2.get(i).getPes() / exp) % 10]++;
        }

        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];


        for (i = n-1; i >= 0; i--) {
            output[count[ (arr2.get(i).getPes() / exp) % 10] -1 ] = arr2.get(i);
            count[ (arr2.get(i).getPes() / exp) % 10]--;
        }

        for (i = 0; i < n; i++)
            arr2.set(i,output[i]);
    }

    public static void radixSortJugador(List<Jugador> arr, int total_players){
        int b=0;
        Jugador[] ordena = new Jugador[total_players];
        for(Jugador k  : arr){
            ordena[b] = k;
            b++;
        }
        int exp=0;
        Jugador[][] matrix = ordenaNacionalitat(ordena);

        List<Pesos> pesos = new ArrayList<>();
         for(int i = 0; i < matrix.length; i++){
            pesos.removeAll(pesos);
            Equip[] e = new Equip[1];

            int cont = 0;
            for(Jugador player:  matrix[i]){
                if(player!=null){
                    Pesos pes = new Pesos();
                    pesos.add(cont, pes);
                    pesos.get(cont).setJug(player);
                    pesos.get(cont).setPes(Math.abs((int)(1000000000*(escalaU(player.getWinrate(), 0, trobaMaxValWR(e, matrix[i], 2))*0.6 + 0.4*escalaU(player.getKda(), 0, trobaMaxValWR(e, matrix[i], 3))))));
                    cont++;
                }
            }
            for (exp = 1; 1000000000/exp > 0; exp *= 10) {
                countSortKDA(pesos, cont, exp);
            }
            for (int j=0; j<pesos.size(); j++) {
                matrix[i][j] = pesos.get(j).getJug();
            }
        }
        int aux=0;
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                if(matrix[i][j]!=null){
                    ordena[aux] = matrix[i][j];
                    aux++;
                }
            }
        }
        arr.removeAll(arr);
        arr.addAll(Arrays.asList(ordena));
    }


    @Override
    public String toString() {
        return "Equip{" +
        "team='" + team + '\'' +
        ", winrate=" + winrate +
        ", jugadores=" + jugadores +
        '}';
    }
}
