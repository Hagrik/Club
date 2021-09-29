package Club;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {
    final private Scanner input = new Scanner(System.in);

    public int getIntInput() {
        int choice = 0;
        while (choice == 0) {
            try {
                choice = input.nextInt();
                if (choice == 0) throw new InputMismatchException();
                input.nextLine();
            } catch (InputMismatchException e) {
                e.printStackTrace();
                input.nextInt();
            }
        }
        return choice;
    }

    public void printClubOptions() {
        System.out.println("1) Club Mercury");
        System.out.println("2) Club Neptune");
        System.out.println("3) Club Jupiter");
        System.out.println("4) Multi Clubs");
    }

    public int getChoice() {
        int choice;
        System.out.println("WELCOME TO OZONE FITNESS CENTER");
        System.out.println("===============================");
        System.out.println("1) Add Member");
        System.out.println("2) Remove Member");
        System.out.println("3) Display Member Information");
        System.out.println("Please select an option (or Enter -1 to quit):");
        choice = getIntInput();
        return choice;
    }

    public String addMembers(LinkedList<Member> members) {
        String name;
        int club;
        String mem;
        double fees;
        int memberID;
        Member mbr;
        Calculator<Integer> calculator;

        System.out.print("Enter member name: ");
        name = input.nextLine();
        System.out.println();
        printClubOptions();
        System.out.println("Enter club ID 1-4: ");
        club = getIntInput();
        while (club < 1 || club > 4) {
            System.out.println("Wrong choice");
            club = getIntInput();
        }
        if (members.size() > 0)
            memberID = members.getLast().getMemberID() + 1;
        else
            memberID = 1;
        if (club != 4) {
            calculator = (n) -> switch (n) {
                case 1 -> 900;
                case 2 -> 950;
                case 3 -> 1000;
                default -> -1;
            };
            fees = calculator.calculateFees(club);
            mbr = new SingleClubMember('S', memberID, name, fees, club);
            members.add(mbr);
            mem = mbr.toString();
            System.out.println("\nSTATUS: SingleClub Member added\n");
        } else {
            calculator = (n) -> switch (n) {
                case 4 -> 1200;
                default -> -1;
            };
            fees = calculator.calculateFees(club);
            mbr = new MultiClubMember('M', memberID, name, fees, 100);
            members.add(mbr);
            mem = mbr.toString();
            System.out.println("\nSTATUS: MultiClub Member added\n");
        }
        return mem;
    }

    public void removeMember(LinkedList<Member> members) {
        int memberID;
        System.out.print("Enter member ID: ");
        memberID = getIntInput();
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getMemberID() == memberID) {
                members.remove(i);
                System.out.println("Member removed");
                return;
            }
        }
        System.out.println("Member not found");
    }

    public void printMemberInfo(LinkedList<Member> members) {
        int memberID;

        System.out.print("\nEnter Member ID to display information: ");
        memberID = getIntInput();
        for (Member member : members) {
            if (member.getMemberID() == memberID) {
                String[] memberInfo =
                        member.toString().split(", ");
                System.out.println("\n\nMember Type = " + memberInfo[0]);
                System.out.println("Member ID = " + memberInfo[1]);
                System.out.println("Member Name = " + memberInfo[2]);
                System.out.println("Membership Fees = " + memberInfo[3]);
                if (memberInfo[0].equals("S")) {
                    System.out.println("Club ID = " + memberInfo[4]);
                } else {
                    System.out.println("Membership Points = " + memberInfo[4]);
                }
                return;
            }
        }
        System.out.println("\nMember ID not found\n");
    }
}