package com.example.demo;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class InvalidUserException extends Exception {
    public InvalidUserException(String message) {
        super(message);
    }
}
class User
{
    private String username;
    private String password;
    public User(String username,String password) throws InvalidUserException {
        isslegal(username);
        isslegalpas(password);

        this.username = username;
        this.password = password;

    }
    public String getUsername()
    {
        return this.username;
    }
    public String getpassword()
    {
        return this.password;
    }
    private void isslegal(String username) throws InvalidUserException
    {
        if(username.length()>50)
        {
            throw new InvalidUserException("Username is too long, try something shorter ");
        }
        String [] splitparts=username.split("@");
        if(splitparts.length!=2)
        {
            throw new InvalidUserException("Please enter a valid Email as username");
        }
        String first_part=splitparts[0];
        String rest=splitparts[1];
        if(first_part.length()==0)
        {
            throw new InvalidUserException("Please enter a valid Email as username");
        }
        String[] splitparts_for_the_rest=rest.split("\\.");
        if(splitparts_for_the_rest.length<2)
        {
            throw new InvalidUserException("Please enter a valid Email as username");
        }
        String second_part=splitparts_for_the_rest[0];
        String third_part=splitparts_for_the_rest[1];
        if(second_part.length()==0||third_part.length()<2)
        {
            throw new InvalidUserException("Please enter a valid Email as username");
        }
        for(int i=0;i<first_part.length();i++)
        {
            char a=first_part.charAt(i);
            if(!Character.isLetterOrDigit(a)&&a!='-'&&a!='+'&&a!='%'&&a!='_')
            {
                throw new InvalidUserException("Please enter a valid Email as username");
            }
        }
        if (!Character.isLetterOrDigit(second_part.charAt(0)))
        {
            throw new InvalidUserException("Please enter a valid Email as username");
        }
        for(int i=0;i<second_part.length();i++)
        {
            char a=second_part.charAt(i);
            if(!Character.isLetterOrDigit(a)&&a!='-'&&a!='.')
            {
                throw new InvalidUserException("Please enter a valid Email as username");
            }
        }
        for(int i=0;i<third_part.length();i++)
        {char a=third_part.charAt(i);
            if(!Character.isLetterOrDigit(a))
            {
                throw new InvalidUserException("Please enter a valid Email as username");
            }

        }




    }
    private void isslegalpas(String password) throws InvalidUserException
    {
        if (password.length()>12)
        {
            throw new InvalidUserException("Your password is too long, try a shorter one");
        }
        if(password.length()<8)
        {
            throw new InvalidUserException("Your password is too short, add more characters");
        }
        boolean hasdigit=false;
        boolean hadsign=false;
        boolean hadletter=false;
        for(int i=0;i<password.length();i++)
        {
            char a=password.charAt(i);
            if(Character.isDigit(a))
            {
                hasdigit=true;
            }
            else if(Character.isLetter(a))
            {
                hadletter=true;
            }
            else
            {
                hadsign=true;
            }

        }
        if( !(hadletter && hadsign && hasdigit))
        {
            throw new InvalidUserException("Please enter a valid password");
        }
    }
}
class UsersApp
{ public static void main (String[] args) throws FileNotFoundException {
    ArrayList<User> users = new ArrayList<>();

    try {
        BufferedReader Br = new BufferedReader(new FileReader("Users.txt"));
        String line = Br.readLine();
        while (line != null) {
            String line_without_morethanonespace=line.replaceAll(" +"," ");

            String[] parts = line_without_morethanonespace.split(" ");
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

