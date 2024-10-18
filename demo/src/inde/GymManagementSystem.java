package inde;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

// Class for a Gym Member
class Member {
    private String name;
    private int age;
    private String membershipType;

    public Member(String name, int age, String membershipType) {
        this.name = name;
        this.age = age;
        this.membershipType = membershipType;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getMembershipType() {
        return membershipType;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Membership: " + membershipType;
    }
}

// Main Gym Management System Frame
public class GymManagementSystem extends Frame {
    private ArrayList<Member> members = new ArrayList<>();

    // Constructor for setting up the UI
    public GymManagementSystem() {
        setTitle("Gym Management System");
        setSize(400, 300);
        setLayout(new FlowLayout());
        setResizable(false);

        // Buttons
        Button addMemberButton = new Button("Add Member");
        Button viewMembersButton = new Button("View Members");
        Button deleteMemberButton = new Button("Delete Member");
        Button exitButton = new Button("Exit");

        // Add buttons to the frame
        add(addMemberButton);
        add(viewMembersButton);
        add(deleteMemberButton);
        add(exitButton);

        // Add Member Action
        addMemberButton.addActionListener(e -> new AddMemberDialog(this));

        // View Members Action
        viewMembersButton.addActionListener(e -> viewMembers());

        // Delete Member Action
        deleteMemberButton.addActionListener(e -> deleteMember());

        // Exit Button Action
        exitButton.addActionListener(e -> System.exit(0));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    // Function to display the list of members
    private void viewMembers() {
        StringBuilder memberInfo = new StringBuilder("Gym Members:\n\n");
        if (members.isEmpty()) {
            memberInfo.append("No members found.");
        } else {
            for (Member member : members) {
                memberInfo.append(member.toString()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(this, memberInfo.toString());
    }

    // Function to add a new member
    public void addMember(Member member) {
        members.add(member);
        JOptionPane.showMessageDialog(this, "Member added successfully!");
    }

    // Function to delete a member
    private void deleteMember() {
        if (members.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No members to delete.");
            return;
        }

        // Create a list of member names to select for deletion
        String[] memberNames = members.stream()
                                      .map(Member::getName)
                                      .toArray(String[]::new);

        String selectedMember = (String) JOptionPane.showInputDialog(this,
                "Select a member to delete:",
                "Delete Member",
                JOptionPane.QUESTION_MESSAGE,
                null,
                memberNames,
                memberNames[0]);

        if (selectedMember != null) {
            members.removeIf(member -> member.getName().equals(selectedMember));
            JOptionPane.showMessageDialog(this, "Member deleted successfully!");
        }
    }

    // Main function
    public static void main(String[] args) {
        new GymManagementSystem();
    }
}

// Add Member Dialog
class AddMemberDialog extends Dialog {
    private TextField nameField, ageField;
    private Choice membershipChoice;

    public AddMemberDialog(Frame parent) {
        super(parent, "Add New Member", true);
        setLayout(new FlowLayout());
        setSize(300, 200);

        // UI Components
        Label nameLabel = new Label("Name:");
        Label ageLabel = new Label("Age:");
        Label membershipLabel = new Label("Membership Type:");

        nameField = new TextField(20);
        ageField = new TextField(3);
        membershipChoice = new Choice();
        membershipChoice.add("Standard");
        membershipChoice.add("Premium");

        Button addButton = new Button("Add");
        Button cancelButton = new Button("Cancel");

        // Add components to the dialog
        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(membershipLabel);
        add(membershipChoice);
        add(addButton);
        add(cancelButton);

        // Add button action
        addButton.addActionListener(e -> addMember(parent));

        // Cancel button action
        cancelButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    // Function to add member
    private void addMember(Frame parent) {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String membershipType = membershipChoice.getSelectedItem();

        Member newMember = new Member(name, age, membershipType);
        ((GymManagementSystem) parent).addMember(newMember);
        dispose();
    }
}