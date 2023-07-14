/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MovieGenre;
import model.Movies;
import model.Schedule;

/**
 *
 * @author acer
 */
public class MovieDAO extends DBContext {
    
    public void dltMovByID(int id) {
        try {
            String sql = "DELETE FROM Movies WHERE movID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public List<Movies> getAllAllMovies() {
        List<Movies> mv = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Movies";
            PreparedStatement st = connection.prepareStatement(sql);
            int i = 0;
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Movies m;
                if (rs.getString("img").substring(0, 2).equals("??")) {
                    m = new Movies(i, rs.getInt("movid"), rs.getString("movname"), rs.getDate("startdate"), rs.getDouble("time(min)"), rs.getString("language"), rs.getString("origin"), rs.getDouble("avrrate"), rs.getString("notes"), rs.getString("status"), rs.getString("studio"), rs.getString("img").substring(2));
                } else if (rs.getString("img").substring(0, 1).equals("?")) {
                    m = new Movies(i, rs.getInt("movid"), rs.getString("movname"), rs.getDate("startdate"), rs.getDouble("time(min)"), rs.getString("language"), rs.getString("origin"), rs.getDouble("avrrate"), rs.getString("notes"), rs.getString("status"), rs.getString("studio"), rs.getString("img").substring(1));
                } else {
                    m = new Movies(i, rs.getInt("movid"), rs.getString("movname"), rs.getDate("startdate"), rs.getDouble("time(min)"), rs.getString("language"), rs.getString("origin"), rs.getDouble("avrrate"), rs.getString("notes"), rs.getString("status"), rs.getString("studio"), rs.getString("img"));
                }
                mv.add(m);
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return mv;
    }

    public List<Movies> getAllMovies() {
        List<Movies> mv = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Movies WHERE Status != N'Dừng chiếu'";
            PreparedStatement st = connection.prepareStatement(sql);
            int i = 0;
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Movies m;
                if (rs.getString("img").substring(0, 2).equals("??")) {
                    m = new Movies(i, rs.getInt("movid"), rs.getString("movname"), rs.getDate("startdate"), rs.getDouble("time(min)"), rs.getString("language"), rs.getString("origin"), rs.getDouble("avrrate"), rs.getString("notes"), rs.getString("status"), rs.getString("studio"), rs.getString("img").substring(2));
                } else if (rs.getString("img").substring(0, 1).equals("?")) {
                    m = new Movies(i, rs.getInt("movid"), rs.getString("movname"), rs.getDate("startdate"), rs.getDouble("time(min)"), rs.getString("language"), rs.getString("origin"), rs.getDouble("avrrate"), rs.getString("notes"), rs.getString("status"), rs.getString("studio"), rs.getString("img").substring(1));
                } else {
                    m = new Movies(i, rs.getInt("movid"), rs.getString("movname"), rs.getDate("startdate"), rs.getDouble("time(min)"), rs.getString("language"), rs.getString("origin"), rs.getDouble("avrrate"), rs.getString("notes"), rs.getString("status"), rs.getString("studio"), rs.getString("img"));
                }
                mv.add(m);
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return mv;
    }

    public List<Movies> getAllMoviesNowShowing() {
        List<Movies> mv = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Movies WHERE Status = N'Đang chiếu'";
            PreparedStatement st = connection.prepareStatement(sql);
            int i = 0;
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Movies m;
                if (rs.getString("img").substring(0, 2).equals("??")) {
                    m = new Movies(i, rs.getInt("movid"), rs.getString("movname"), rs.getDate("startdate"), rs.getDouble("time(min)"), rs.getString("language"), rs.getString("origin"), rs.getDouble("avrrate"), rs.getString("notes"), rs.getString("status"), rs.getString("studio"), rs.getString("img").substring(2));
                } else if (rs.getString("img").substring(0, 1).equals("?")) {
                    m = new Movies(i, rs.getInt("movid"), rs.getString("movname"), rs.getDate("startdate"), rs.getDouble("time(min)"), rs.getString("language"), rs.getString("origin"), rs.getDouble("avrrate"), rs.getString("notes"), rs.getString("status"), rs.getString("studio"), rs.getString("img").substring(1));
                } else {
                    m = new Movies(i, rs.getInt("movid"), rs.getString("movname"), rs.getDate("startdate"), rs.getDouble("time(min)"), rs.getString("language"), rs.getString("origin"), rs.getDouble("avrrate"), rs.getString("notes"), rs.getString("status"), rs.getString("studio"), rs.getString("img"));
                }
                mv.add(m);
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return mv;
    }

    public Movies getMovieById(int id) {
        List<Movies> mv = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Movies WHERE movID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            int i = 0;
            if (rs.next()) {
                Movies m;

                if (rs.getString("img").substring(0, 2).equals("??")) {
                    m = new Movies(i, rs.getInt("movid"), rs.getString("movname"), rs.getDate("startdate"), rs.getDouble("time(min)"), rs.getString("language"), rs.getString("origin"), rs.getDouble("avrrate"), rs.getString("notes"), rs.getString("status"), rs.getString("studio"), rs.getString("img").substring(2), rs.getDate("EndDate"));
                } else if (rs.getString("img").substring(0, 1).equals("?")) {
                    m = new Movies(i, rs.getInt("movid"), rs.getString("movname"), rs.getDate("startdate"), rs.getDouble("time(min)"), rs.getString("language"), rs.getString("origin"), rs.getDouble("avrrate"), rs.getString("notes"), rs.getString("status"), rs.getString("studio"), rs.getString("img").substring(1), rs.getDate("EndDate"));
                } else {
                    m = new Movies(i, rs.getInt("movid"), rs.getString("movname"), rs.getDate("startdate"), rs.getDouble("time(min)"), rs.getString("language"), rs.getString("origin"), rs.getDouble("avrrate"), rs.getString("notes"), rs.getString("status"), rs.getString("studio"), rs.getString("img"), rs.getDate("EndDate"));
                }

                return m;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<String> getAllMovieFormById(int id) {
        List<String> list = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT formName FROM Schedule JOIN Form ON Schedule.formID = Form.formID JOIN Cinema ON Schedule.cinID = Cinema.cinID WHERE movID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("formName"));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<String> getAllMovieFormByIdAndLocationAndTime(int id, String city, Date startTime, Date endTime) {
        List<String> list = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT formName FROM Schedule JOIN Form ON Schedule.formID = Form.formID JOIN Cinema ON Schedule.cinID = Cinema.cinID WHERE City = ? AND startDate >= ? AND endDate <= ? AND movID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, city);
            st.setDate(2, startTime);
            st.setDate(3, endTime);
            st.setInt(4, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("formName"));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Movies> getAllMoviesNotShownYet() {
        List<Movies> mv = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Movies WHERE Status = N'Chưa chiếu'";
            PreparedStatement st = connection.prepareStatement(sql);
            int i = 0;
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Movies m;
                if (rs.getString("img").substring(0, 2).equals("??")) {
                    m = new Movies(i, rs.getInt("movid"), rs.getString("movname"), rs.getDate("startdate"), rs.getDouble("time(min)"), rs.getString("language"), rs.getString("origin"), rs.getDouble("avrrate"), rs.getString("notes"), rs.getString("status"), rs.getString("studio"), rs.getString("img").substring(2));
                } else if (rs.getString("img").substring(0, 1).equals("?")) {
                    m = new Movies(i, rs.getInt("movid"), rs.getString("movname"), rs.getDate("startdate"), rs.getDouble("time(min)"), rs.getString("language"), rs.getString("origin"), rs.getDouble("avrrate"), rs.getString("notes"), rs.getString("status"), rs.getString("studio"), rs.getString("img").substring(1));
                } else {
                    m = new Movies(i, rs.getInt("movid"), rs.getString("movname"), rs.getDate("startdate"), rs.getDouble("time(min)"), rs.getString("language"), rs.getString("origin"), rs.getDouble("avrrate"), rs.getString("notes"), rs.getString("status"), rs.getString("studio"), rs.getString("img"));
                }
                mv.add(m);
                i++;
            }
        } catch (Exception e) {
        }
        return mv;
    }

    public void updateInfo(int movID, String movName, String startdate, double time, String lang, String org, String status, String studio, String img) {
        try {
            String sql = "UPDATE Movies SET movName = ?, StartDate = ?, [Time(min)] = ?, Language = ?, Origin = ?, Status = ?, Studio = ?, Img = ? WHERE movID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, movName);
            st.setDate(2, Date.valueOf(startdate));
            st.setDouble(3, time);
            st.setString(4, lang);
            st.setString(5, org);
            st.setString(6, status);
            st.setString(7, studio);
            st.setString(8, img);
            st.setInt(9, movID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Movies checkID(int ID) {
        try {
            String sql = "SELECT * FROM Movies WHERE movID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, ID);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                Movies m;
                if (rs.getString("img").substring(0, 2).equals("??")) {
                    m = new Movies(0, rs.getInt("movid"), rs.getString("movname"), rs.getDate("startdate"), rs.getDouble("time(min)"), rs.getString("language"), rs.getString("origin"), rs.getDouble("avrrate"), rs.getString("notes"), rs.getString("status"), rs.getString("studio"), rs.getString("img").substring(2));
                } else if (rs.getString("img").substring(0, 1).equals("?")) {
                    m = new Movies(0, rs.getInt("movid"), rs.getString("movname"), rs.getDate("startdate"), rs.getDouble("time(min)"), rs.getString("language"), rs.getString("origin"), rs.getDouble("avrrate"), rs.getString("notes"), rs.getString("status"), rs.getString("studio"), rs.getString("img").substring(1));
                } else {
                    m = new Movies(0, rs.getInt("movid"), rs.getString("movname"), rs.getDate("startdate"), rs.getDouble("time(min)"), rs.getString("language"), rs.getString("origin"), rs.getDouble("avrrate"), rs.getString("notes"), rs.getString("status"), rs.getString("studio"), rs.getString("img"));
                }
                return m;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void insertMovie(int id, String name, Date startDate, double time, String lang, String origin, double rate, String note, String stt, String studio, String img, Date end) {
        try {
            String sql = "INSERT INTO Movies VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, name);
            st.setDate(3, startDate);
            st.setDouble(4, time);
            st.setString(5, lang);
            st.setString(6, origin);
            st.setDouble(7, rate);
            st.setString(8, note);
            st.setString(9, stt);
            st.setString(10, studio);
            st.setString(11, img);
            st.setDate(12, end);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public List<MovieGenre> getAllGenre() {
        List<MovieGenre> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM MoviesGenre";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                MovieGenre mg = new MovieGenre(rs.getInt("genreID"), rs.getString("genreName"));
                list.add(mg);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }  
        return list;
    }
    
    public void insertStar(int movID, String star) {
        try {
            String sql = "INSERT INTO Stars VALUES (?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            st.setString(2, star);
            st.setString(3, "");
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void insertDirector(int movID, String directorName) {
        try {
            String sql = "INSERT INTO Directors VALUES (?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            st.setString(2, directorName);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void insertGenre(int movID, int genreID) {
        try {
            String sql = "INSERT INTO Genre VALUES (?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            st.setInt(2, genreID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public MovieGenre getGenreByID(int genreID) {
        try {
            String sql = "SELECT * FROM MoviesGenre WHERE genreID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, genreID);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                MovieGenre mg = new MovieGenre(rs.getInt("genreID"), rs.getString("genreName"));
                return mg;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    
}
