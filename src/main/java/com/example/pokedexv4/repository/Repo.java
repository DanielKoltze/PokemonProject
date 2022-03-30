package com.example.pokedexv4.repository;

import com.example.pokedexv4.model.Pokemon;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repo {
    private final String URL = "jdbc:mysql://localhost:3306/Pokedex?useSSL=false&serverTimezone=UTC"; //efter3306 skriver hvad det er for en tabel
    private final String user = "root";
    private final String password = "rootroot";
    private Connection connection;
    public Repo(){
        setConnection();
    }

    public List<Pokemon> selectAll(){
        List<Pokemon> pokemons = new ArrayList<>();
        String query = "SELECT * FROM pokemon;";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int id = resultSet.getInt(1); // her kan man også skrive 1, for at få første column
                int attack = resultSet.getInt(2);
                int defence = resultSet.getInt(3);
                int hp = resultSet.getInt(4);
                String name = resultSet.getString(5);
                String primary_type = resultSet.getString(6);
                String secondary_type = resultSet.getString(7);
                int special_attack = resultSet.getInt(8);
                int special_defence = resultSet.getInt(9);
                int speed = resultSet.getInt(10);


                pokemons.add(new Pokemon(id,attack,defence,hp,name,primary_type,secondary_type,special_attack,special_defence,speed));

            }

        }catch(Exception e){
            System.out.println("Kunne ikke vise alle pokemons");
        }
        return pokemons;
    }

    private void setConnection() {
        try {
            connection = DriverManager.getConnection(URL, user, password);
        } catch (Exception e) {
            System.out.println("Databasen er ikke connected");
        }
    }
    public void insert(int id, String name, int speed, int special_defence, int special_attack, int defence, int attack, int hp, String primary_type, String secondary_type){
        String query = "INSERT INTO pokemon VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(5, name);
            preparedStatement.setInt(10, speed);
            preparedStatement.setInt(9, special_defence);
            preparedStatement.setInt(8, special_attack);
            preparedStatement.setInt(3, defence);
            preparedStatement.setInt(2, attack);
            preparedStatement.setInt(4, hp);
            preparedStatement.setString(6, primary_type);
            preparedStatement.setString(7, secondary_type);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Kunne ikke indsætte pokemon");
        }

    }
    public void delete(int id){

        String query = "DELETE FROM pokemon WHERE pokedex_number = " + id;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.println("Du slettede pokemonen med id: " + id);

        } catch (Exception e) {
            System.out.print("kunne ikke slette pokemon");


        }
    }
    public void update(int id, String name, int speed, int special_defence, int special_attack, int defence, int attack, int hp, String primary_type, String secondary_type){
        String query = "UPDATE pokemon SET attack = '" + attack + "', defence= '" + defence + "', hp= '" + hp + "', name = '" + name + "', primary_type = '" + primary_type + "', secondary_type = '" + secondary_type + "', special_attack = '" + special_attack + "', special_defence = '" + special_defence + "', speed = '" + special_attack + "' WHERE pokedex_number = " + id + ";";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        }catch(Exception e){
            System.out.println("Kunne ikke opdatere pokemon");
        }
    }


    public Pokemon findById(int id){
        String query = "SELECT * FROM pokemon WHERE pokedex_number = " + id;
        Pokemon pokemon = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
                resultSet.next();
                int number = resultSet.getInt(1);
                int attack = resultSet.getInt(2);
                int defence = resultSet.getInt(3);
                int hp = resultSet.getInt(4);
                String name = resultSet.getString(5);
                String primary_type = resultSet.getString(6);
                String secondary_type = resultSet.getString(7);
                int special_attack = resultSet.getInt(8);
                int special_defence = resultSet.getInt(9);
                int speed = resultSet.getInt(10);
                pokemon = new Pokemon(number,attack,defence,hp,name,primary_type,secondary_type,special_attack,special_defence,speed);

        }catch(Exception e){
            System.out.println("findById virkede ikke");
        }
        return pokemon;
    }



}
