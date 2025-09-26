package learning;

public class Main {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        String ownerName = args.length > 0 ? args[0] : "";
        if (ownerName == null || ownerName.trim().isEmpty()) {
            System.out.print("สร้างกระปุกออมสินของ(ใส่ชื่อ): ");
            ownerName = scanner.nextLine();
            while (ownerName == null || ownerName.trim().isEmpty()) {
                System.out.print("กระปุกไม่มีชื่อ กรุณาใส่ชื่ออีกครั้ง: ");
                ownerName = scanner.nextLine();
            }
        }

        PiggyBank piggyBank = new PiggyBank(ownerName.trim());

        piggyBank.showMoney();

        if (args.length > 1) {
            for (int i = 1; i < args.length; i++) {
                try {
                    double amount = Double.parseDouble(args[i]);
                    piggyBank.addMoney(amount);
                } catch (NumberFormatException e) {
                    System.out.println("ข้ามค่าไม่ถูกต้อง: " + args[i]);
                }
            }
        } else {
            while (true) {
                System.out.println();
                System.out.println("เลือกรายการ:");
                System.out.println("1. ดูเงินในกระปุก");
                System.out.println("2. หยอดเงิน");
                System.out.println("3. ออกจากโปรแกรม");
                System.out.print("พิมพ์หมายเลขเมนู: ");

                String choice = scanner.next();

                if ("1".equals(choice)) {
                    piggyBank.showMoney();
                } else if ("2".equals(choice)) {
                    System.out.print("จำนวนเงินที่ต้องการหยอด: ");
                    String amountToken = scanner.next();
                    try {
                        double amount = Double.parseDouble(amountToken);
                        piggyBank.addMoney(amount);
                    } catch (NumberFormatException e) {
                        System.out.println("จำนวนเงินไม่ถูกต้อง: " + amountToken);
                    }
                } else if ("3".equals(choice)) {
                    break;
                } else {
                    System.out.println("เมนูไม่ถูกต้อง กรุณาเลือกใหม่");
                }
            }
        }

        piggyBank.showMoney();

        scanner.close();
    }
}


