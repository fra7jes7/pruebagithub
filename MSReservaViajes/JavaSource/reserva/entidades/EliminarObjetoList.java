package reserva.entidades;

import java.util.ArrayList;

public class EliminarObjetoList {

	public EliminarObjetoList() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> al = new ArrayList<String>();
		ArrayList<Integer> indices = new ArrayList<Integer>();

		al.add("Víctor");
		al.add("null");
		al.add("Jesus");
		al.add("Frank");
		al.add("null");

		for (String str : al) {
			System.out.println("nombres:" + str);
		}
		System.out.println("-----");
		int contador=0;
		for (String str : al) {
			if(str.equals("null")){
				indices.add(contador);
			}
			contador++;
		}
//		al.remove(0);
		for(Integer i=0;i<indices.size();i++){
			System.out.println("i:"+indices.get(i));
			al.remove(indices.get(i));
		}
		
		System.out.println("-----");
		for (String str : al) {
			System.out.println("nombres:" + str);
		}
	}

}
