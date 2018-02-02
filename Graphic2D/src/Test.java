import java.io.*; 
import java.util.ArrayList;

/**
 * @author Damian Borek
 *
 */
public class Test implements Serializable { 
											
	/**
	 * @param r
	 * @param pe
	 * @param e
	 * @param p
	 */
	public void savetofile(ArrayList<JRectangle> r, ArrayList<JPolygon> pe, ArrayList<JEllipse> e, JPaint p) {
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("E:/bla.ser"));
			for (int i = 0; i < p.liczbakwadratow; i++) {
				o.writeObject(r.get(i));
			}
			for (int i = 0; i < p.liczbawielokatow; i++) {
				o.writeObject(pe.get(i));
			}
			for (int i = 0; i < p.liczbaokregow; i++) {
				o.writeObject(e.get(i));
			}
			o.close();
			System.out.println("okregi "+ p.liczbaokregow+"kwadraty "+p.liczbakwadratow);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @param p
	 */
	public void loadfromfile(JPaint p) {
		try {
			ObjectInputStream o = new ObjectInputStream(new FileInputStream("E:/bla.ser"));

		

			p.liczbaprzeksztalcen = 0;
			
			for (int i = p.PolygonArray.size()-1; i >= 0; i--) {
				
				p.PolygonArray.remove(i);
				

			}
			for (int i = p.RectangleArray.size()-1; i >=0 ; i--) {
				p.RectangleArray.remove(i);
				

			}
			for (int i = p.CircleArray.size()-1; i >=0; i--) {
				p.CircleArray.remove(i);
				
			}
			for(int i=0;i<p.liczbakresek;i++)
			{
				p.tablicakresek[i]=null;
			}
			p.liczbakresek = 0;
			p.liczbawielokatow=0;
			p.liczbakwadratow=0;
			p.liczbaokregow=0;
			System.out.println(p.liczbakwadratow);
			Boolean complete = true;
			while (complete) {
				try {
					Object obj = o.readObject();
					if (obj != null) {
						if (obj.getClass() == p.ble.getClass()) {
							p.RectangleArray.add((JRectangle) obj);
							p.liczbakwadratow++;
						} else if (obj.getClass() == p.czarodziejskiekolko.getClass()) {
							p.CircleArray.add((JEllipse) obj);
							p.liczbaokregow++;
						} else {
							p.PolygonArray.add((JPolygon) obj);
							p.liczbawielokatow++;
							p.liczbaprzeksztalcen++;
						}

					}
				} catch (EOFException ex) {
					complete = false;
				}
			}
			System.out.println(p.liczbakwadratow + " " + p.liczbaokregow);

			o.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
