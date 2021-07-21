
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class Main{

	public static void llistaFitxers(final String pattern, final File folder, List<String> result) {
		for (final File f : Objects.requireNonNull(folder.listFiles())) {
			if (f.isDirectory()) {
				llistaFitxers(pattern, f, result);
			}
			if (f.isFile()) {
				if (f.getName().matches(pattern)) {
					result.add(f.getName());
				}
			}
		}
	}

    public static void main(String[] args){
		Path current2 = Paths.get("src/main/resources/");
		String datasets = current2.toAbsolutePath().toString();

		System.out.println("\n\nQuin dataset vols consultar? ");
		final File folder = new File(datasets);

		List<String> result = new ArrayList<>();

		llistaFitxers(".*\\.json", folder, result);
		int num = 1;
		for (String s : result) {
			System.out.print(num + ":" + s + "   ");
			num++;
		}
		System.out.print("\n\nTria: ");

		Scanner in = new Scanner(System.in);
		int fitxer = in.nextInt();


		Path current = Paths.get("src/main/resources/" + result.get(fitxer-1));
        String arxiu = current.toAbsolutePath().toString();

		boolean sortida = false;



        try(BufferedReader reader = new BufferedReader(new FileReader(arxiu))){

			Gson gson = new GsonBuilder().create();
			Equip[] equips = gson.fromJson(reader, Equip[].class);

			System.out.println("\n\nFichero JSON llegit correctament.\n\n");

			while(!sortida){
				System.out.println("Introdueix el numero del criteri amb el que vols endreçar-ho");
				System.out.println("\t1. WINRATE\n\t2. NACIONALIDAD\n\t3. COMBINACIÓ DE PRIORITATS\n\t4. SORTIR");
				System.out.print("Opció: ");
				in = new Scanner(System.in);
				String s = in.nextLine();

				if(s.equals("4")){
					System.out.println("\n-------------------------------------------");
					System.out.print("\n\n  Gràcies per fer servir el programa.\n\n");
					System.out.println("\n-------------------------------------------");
					sortida = true;
				}else{
					System.out.println("\n\nHas triat la opció: "+ s );
					System.out.println("Amb quin mètode de ordenació vols que s'endreçi la informació?");
					if(s.equals("2")){
						System.out.println("\t1. QuickSort\n\t2. MergeSort\n\t3. BucketSort");
					}else{
						System.out.println("\t1. QuickSort\n\t2. MergeSort\n\t3. BucketSort\n\t4. RadixSort");
					}

					System.out.print("Opció: ");
					Scanner in2 = new Scanner(System.in);
					String s2 = in2.nextLine();

					System.out.println("\n\nHas triar la opció: "+ s2);

					Equip equip = new Equip();

					List<Jugador> j = new ArrayList<Jugador>();
					for(Equip e : equips){
						for(Jugador play : e.getJugadores()){
							play.setWinrate(e.getWinrate());
							play.setEquip_pertany(e.getTeam());
							j.add(play);
						}
					}

					long startTime1 = 0;
					long endTime1   = 0;
					long startTime2 = 0;
					long endTime2   = 0;


					switch (s2) {
						case "1":
							startTime1 = System.nanoTime();
							startTime2 = System.currentTimeMillis();
							switch (s) {
								case "1":
									Equip.quickSortEquip(equips, 0, equips.length - 1);
									break;
								case "2":
									Equip.quickSortJugador(j, 0, j.size() - 1, s);
									break;
								case "3":
									Equip.quickSortJugador(j, 0, j.size() - 1, s);
									break;
							}
							endTime1 = System.nanoTime();
							endTime2 = System.currentTimeMillis();
							break;
						case "2":
							Jugador[] jug = new Jugador[j.size()];
							jug = j.toArray(jug);
							startTime1 = System.nanoTime();
							startTime2 = System.currentTimeMillis();
							switch (s) {
								case "1":
									equip.mergeSortEquip(equips, 0, equips.length - 1);
									break;
								case "2":
									equip.mergeSortJugador(j, s, Equip.trobaMaxValWR(equips, jug, 2), Equip.trobaMaxValWR(equips, jug, 3));
									break;
								case "3":
									equip.mergeSortJugador(j, s, Equip.trobaMaxValWR(equips, jug, 2), Equip.trobaMaxValWR(equips, jug, 3));
									break;
								default:
									throw new IllegalStateException("Unexpected value: " + s);
							}
							endTime1 = System.nanoTime();
							endTime2 = System.currentTimeMillis();
							break;
						case "3":
							Jugador[] p = new Jugador[1];
							startTime1 = System.nanoTime();
							startTime2 = System.currentTimeMillis();
							switch (s) {
								case "1":
									Equip.bucketSortEquips(equips, Equip.trobaMaxValWR(equips, p, 1), Equip.trobaMaxVal2(equips));
									break;
								case "2":
									j = Equip.bucketSortJugador(equips, j.size(), s);
									break;
								case "3":
									j = Equip.bucketSortJugador(equips, j.size(), s);
									break;
							}
							endTime1 = System.nanoTime();
							endTime2 = System.currentTimeMillis();
							break;
						case "4":
							startTime1 = System.nanoTime();
							startTime2 = System.currentTimeMillis();
							switch (s) {
								case "1":
									Equip.radixSortEquips(equips);
									break;
								case "2":
									Equip.radixSortJugador(j, j.size());
									break;
								case "3":
									Equip.radixSortJugador(j, j.size());
									break;
							}
							endTime1 = System.nanoTime();
							endTime2 = System.currentTimeMillis();
							break;
					}
					long totalTime = endTime1 - startTime1;
					long totalTimeMS = endTime2 - startTime2;
					long totalTimeS = totalTimeMS/1000;

					printSort(equips, j, s, s2, totalTime, totalTimeMS, totalTimeS);
				}
			}


			} catch (IOException e) {
				e.printStackTrace();
			}
    }

		private static DecimalFormat df2 = new DecimalFormat("#.####");

    private static void printSort(Equip[] equips, List<Jugador> j, String criteri, String method, long elapsedTime, long elapsedTimeMS, long elapsedTimeS){

        if(criteri.equals("1")){
        	System.out.println("\n\n------- Els equips endreçats en funció del seu winrate -------\n");
        	for(int i = 0; i<equips.length ;i++){
            	System.out.println("Nom team " + (i+1) + ": " + equips[i].getTeam() + "\n" + "Winrate: " + equips[i].getWinrate() + "\n");
          	}
        }else if(criteri.equals("2")){
			for(Jugador player : j){
				System.out.println("Nom Jugador: " + player.getName() + "\n" + "Nacionalitat: " + player.getNationality() + "\n" + "Equip: " + player.getEquip_pertany() + "\n");
			}
        }else if(criteri.equals("3")){
			for(Jugador player : j){
				System.out.println("Nom Jugador: " + player.getName() + "\n" + "Equip: " + player.getEquip_pertany() + "\n" + "KDA: " + df2.format(player.getKda())  + "\n"+ "Winrate: " + player.getWinrate() + "\n" + "Nacionalitat: " + player.getNationality() + "\n");
			}
        }

		System.out.println("\n-------------------------------------------------------------");
		if(method.equals("1")){
			System.out.println("El mètode de ordenació emprat ha estat: QuickSort"+ "\nHa trigat " + elapsedTime + "ns  " + elapsedTimeMS + "ms  " + elapsedTimeS + "s " + " en executar-se.");
		}else if(method.equals("2")){
			System.out.println("El mètode de ordenació emprat ha estat: MergeSort"+ "\nHa trigat " + elapsedTime + "ns  " + elapsedTimeMS + "ms  " + elapsedTimeS + "s " + " en executar-se.");
		}else if(method.equals("3")){
			System.out.println("El mètode de ordenació emprat ha estat: BucketSort"+ "\nHa trigat " + elapsedTime + "ns  " + elapsedTimeMS + "ms  " + elapsedTimeS + "s " + " en executar-se.");
		}else if(method.equals("4")){
			System.out.println("El mètode de ordenació emprat ha estat: RadixSort"+ "\nHa trigat " + elapsedTime + "ns  " + elapsedTimeMS + "ms  " + elapsedTimeS + "s " + " en executar-se.");
		}
		System.out.println("--------------------------------------------------------------\n\n");

    }

}
