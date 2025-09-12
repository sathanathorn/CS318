public class To_Do_List {
	/**
	 * โปรแกรม To-Do List แบบ Command-Line (Console)
	 *
	 * คุณสมบัติหลัก:
	 * - add <รายการ>: เพิ่มงานใหม่เข้าสู่รายการ
	 * - list: แสดงงานทั้งหมดที่ยังไม่ได้ทำ โดยเรียงลำดับเป็นหมายเลข 1..N
	 * - remove <หมายเลข>: ลบงานตามหมายเลขที่แสดงจากคำสั่ง list
	 * - exit: ออกจากโปรแกรม
	 *
	 * การออกแบบโดยสรุป:
	 * - ใช้ ArrayList<String> สำหรับเก็บงาน เพราะเข้าถึงและลบโดยดัชนีได้ง่าย
	 * - ใช้ลูป while(true) เพื่อรับคำสั่งไปเรื่อย ๆ จนกว่าจะพิมพ์ exit
	 * - รองรับการกรอกคำสั่งที่ขึ้นต้นด้วยช่องว่าง และแยกคำสั่ง/อาร์กิวเมนต์อย่างยืดหยุ่น
	 * - ตรวจสอบความถูกต้องของอินพุต เช่น กรณี add ว่าง หรือหมายเลข remove ไม่ถูกต้อง
	 */

	public static void main(String[] args) {
		// สร้างโครงสร้างข้อมูลหลักสำหรับเก็บงาน (แต่ละงานเป็น String หนึ่งรายการ)
		java.util.ArrayList<String> tasks = new java.util.ArrayList<>();

		// สร้างสแกนเนอร์เพื่อรับอินพุตจากผู้ใช้ทางมาตรฐาน (คอนโซล)
		// หมายเหตุ: หากใช้รหัสภาษาไทย แนะนำให้ตั้งค่า Console/Terminal ให้รองรับ UTF-8
		java.util.Scanner scanner = new java.util.Scanner(System.in);

		// แสดงคำแนะนำการใช้งานเบื้องต้น
		System.out.println("Simple To-Do List (type 'add <item>', 'list', 'remove <num>', 'exit')");

		// วนรับคำสั่งจนกว่าจะเจอ exit หรืออินพุตสิ้นสุด
		while (true) {
			System.out.print("> "); // prompt เล็ก ๆ ให้ผู้ใช้ทราบว่าพร้อมรับคำสั่ง

			if (!scanner.hasNextLine()) {
				// หากไม่มีบรรทัดอินพุตต่อไป (เช่น ปิดสตรีม) ออกจากลูป
				break;
			}

			String line = scanner.nextLine().trim(); // ตัดช่องว่างหัวท้ายเพื่อความสะดวก
			if (line.isEmpty()) {
				// ถ้าผู้ใช้กด Enter เปล่า ๆ ไม่ต้องทำอะไร
				continue;
			}

			// แยกคำสั่งหลักกับอาร์กิวเมนต์: ส่วนแรกคือคำสั่ง ส่วนที่เหลือคือข้อมูลเพิ่มเติม
			// ใช้การค้นหาช่องว่างแรกเพื่อคงข้อความอาร์กิวเมนต์ทั้งหมดแบบเดิม
			String command;
			String argument = "";
			int firstSpace = line.indexOf(' ');
			if (firstSpace == -1) {
				// ไม่มีช่องว่าง แปลว่าเป็นคำสั่งเดี่ยว (เช่น list, exit)
				command = line.toLowerCase();
			} else {
				command = line.substring(0, firstSpace).toLowerCase();
				argument = line.substring(firstSpace + 1).trim();
			}

			switch (command) {
				case "add":
					// รูปแบบที่ถูกต้อง: add <รายการ>
					if (argument.isEmpty()) {
						System.out.println("โปรดระบุรายการงานหลังคำสั่ง add เช่น: add ซื้อนม");
						break;
					}
					// เพิ่มรายการงานใหม่ไปยังท้ายลิสต์
					tasks.add(argument);
					System.out.println("เพิ่มงานเรียบร้อย: " + argument);
					break;

				case "list":
					// แสดงรายการทั้งหมดแบบมีหมายเลขลำดับตั้งแต่ 1
					if (tasks.isEmpty()) {
						System.out.println("ยังไม่มีงานในรายการ");
					} else {
						printTasks(tasks);
					}
					break;

				case "remove":
					// รูปแบบที่ถูกต้อง: remove <หมายเลข>
					if (argument.isEmpty()) {
						System.out.println("โปรดระบุหมายเลขงานที่ต้องการลบ เช่น: remove 2");
						break;
					}
					// พยายามแปลงอาร์กิวเมนต์เป็นตัวเลขลำดับ (เริ่มนับจาก 1)
					int indexToRemove;
					try {
						indexToRemove = Integer.parseInt(argument);
					} catch (NumberFormatException ex) {
						System.out.println("หมายเลขไม่ถูกต้อง: " + argument);
						break;
					}

					if (indexToRemove < 1 || indexToRemove > tasks.size()) {
						System.out.println("ไม่พบงานหมายเลข " + indexToRemove + ". ใช้คำสั่ง list เพื่อตรวจสอบหมายเลขล่าสุด");
						break;
					}

					// ArrayList ใช้ดัชนีเริ่มที่ 0 จึงต้องลบที่ (หมายเลข - 1)
					String removed = tasks.remove(indexToRemove - 1);
					System.out.println("ลบงานเรียบร้อย: " + removed);
					break;

				case "exit":
					// จบการทำงานของโปรแกรม
					System.out.println("กำลังออกจากโปรแกรม...");
					// ออกจากลูป while
					// หมายเหตุ: จะปิด Scanner หลังออกจากลูปเพื่อคืนทรัพยากร
					// ใช้ break ออกจาก switch แล้วออกจาก while ด้วยการเช็คในเงื่อนไขต่อไป
					// แต่ที่นี่เลือกใช้การ break ออกจาก while ด้วยการตั้งแฟลก หรือใช้ return ก็ได้
					// เพื่อความชัดเจน ที่นี่จะใช้การออกด้วยการ return ทันที
					scanner.close();
					return;

				default:
					// คำสั่งไม่รองรับ แสดงตัวอย่างการใช้งาน
					System.out.println("ไม่รู้จักคำสั่ง: " + command);
					System.out.println("ตัวอย่าง: add ซื้อนม | list | remove 2 | exit");
			}
		}

		// ปิด Scanner เมื่อออกจากลูปด้วยเหตุอื่น (เช่น EOF)
		scanner.close();
	}

	/**
	 * แสดงรายการงานทั้งหมด พร้อมหมายเลขลำดับเริ่มที่ 1
	 *
	 * @param tasks รายการงานทั้งหมดในลิสต์
	 */
	private static void printTasks(java.util.List<String> tasks) {
		for (int i = 0; i < tasks.size(); i++) {
			int displayIndex = i + 1; // แปลงดัชนี 0-based ให้เป็น 1-based เพื่อผู้ใช้
			System.out.println(displayIndex + ". " + tasks.get(i));
		}
	}
}
