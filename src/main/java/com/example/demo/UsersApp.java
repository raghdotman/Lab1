package com.example.demo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class UsersApp
{ public static void main (String[] args) throws FileNotFoundException {
    ArrayList<User> users = new ArrayList<>();

    try {
        BufferedReader Br = new BufferedReader(new FileReader("Users.txt"));
        String line = Br.readLine();
        while (line != null) {
            String line_without_morethanonespac=line.replaceAll(" +"," ");

            String[] parts = line_without_morethanonespac.split(" ");
            String username = parts[0];
            String password = parts[1];
            try {
                User usser = new User(username, password);
                users.add(usser);
            } catch (InvalidUserException e) {
                System.out.println(e.getMessage());
            }
            line = Br.readLine();
        }
        Br.close();
        users.sort((u1, u2) -> u1.getUsername().compareTo(u2.getUsername()));

        for (User u : users) {
            System.out.println(u.getUsername() + " " + u.getpassword());
        }
    }
    catch(IOException e)
    {
        System.out.println("Error reading file");
    }


}



}
