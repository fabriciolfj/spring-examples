package com.github.fabriciolfj.javaexamples.repository.impl;

import com.github.fabriciolfj.javaexamples.entity.Vehicle;
import com.github.fabriciolfj.javaexamples.repository.VehicleDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PlainJdbcVehicleDao implements VehicleDao {
    private static final String INSERT_SQL = "INSERT INTO VEHICLE (COLOR, WHEEL, SEAT, VEHICLE_NO) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE VEHICLE SET COLOR=?,WHEEL=?,SEAT=? WHERE VEHICLE_NO=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM VEHICLE";
    private static final String SELECT_ONE_SQL = "SELECT * FROM VEHICLE WHERE VEHICLE_NO = ?";
    private static final String DELETE_SQL = "DELETE FROM VEHICLE WHERE VEHICLE_NO=?";
    private static final String COUNT_ALL_SQL = "SELECT COUNT(*) FROM VEHICLE";
    private static final String SELECT_COLOR_SQL = "SELECT COLOR FROM VEHICLE WHERE VEHICLE_NO=?";

    private final DataSource dataSource;

    public PlainJdbcVehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Vehicle vehicle) {
        var jdbcTemplate = new JdbcTemplate(this.dataSource);
        jdbcTemplate.update(INSERT_SQL, vehicle.getColor(), vehicle.getWheel(), vehicle.getSeat(), vehicle.getVehicleNo());
        /*try (var conn = dataSource.getConnection();
             var ps = conn.prepareStatement(INSERT_SQL)) {
            prepareStatement(ps, vehicle);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        var jdbcTemplate = new JdbcTemplate(this.dataSource);
        var mapper = BeanPropertyRowMapper.newInstance(Vehicle.class);
        return jdbcTemplate.queryForObject(SELECT_ONE_SQL, mapper, vehicleNo);
        /*var result = new Vehicle();
        jdbcTemplate.query(SELECT_ONE_SQL, rs -> {
            result.setVehicleNo(rs.getString("VEHICLE_NO"));
            result.setColor(rs.getString("COLOR"));
            result.setWheel(rs.getInt("RODA"));
            result.setSeat(rs.getInt("SEAT"));
        }, vehicleNo);

        return result; */
        /*try (var conn = dataSource.getConnection();
             var ps = conn.prepareStatement(SELECT_ONE_SQL)) {
            ps.setString(1, vehicleNo);
            Vehicle vehicle = null;
            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    vehicle = toVehicle(rs);
                }
            }
            return vehicle;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    @Override
    public List<Vehicle> findAll() {
        var jdbTemplate = new JdbcTemplate(this.dataSource);
        var rows = jdbTemplate.queryForList(SELECT_ALL_SQL);
        return rows.stream().map(rs -> {
            var result = new Vehicle();
            result.setVehicleNo((String) rs.get("VEHICLE_NO"));
            result.setColor((String) rs.get("COLOR"));
            result.setWheel((Integer) rs.get("RODA"));
            result.setSeat((Integer) rs.get("SEAT"));

            return result;
        }).collect(Collectors.toList());
       /* try(var conn = dataSource.getConnection();
            var ps = conn.prepareStatement(SELECT_ALL_SQL)) {
            final List<Vehicle> vehicles = new ArrayList<>();

            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    vehicles.add(toVehicle(rs));
                }
            }

            return vehicles;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }*/
    }

    @Override
    public void update(Vehicle vehicle) {
        var jdbcTemplate = new JdbcTemplate(this.dataSource);
        jdbcTemplate.update(UPDATE_SQL, ps -> prepareStatement(ps, vehicle));

        /*
        try (var conn = dataSource.getConnection();
             var ps = conn.prepareStatement(UPDATE_SQL)) {
            prepareStatement(ps, vehicle);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
    @Override
    public void delete(Vehicle vehicle) {
        try (var conn = dataSource.getConnection();
             var ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setString(1, vehicle.getVehicleNo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(List<Vehicle> vehicles) {
        var jdbTemplate = new JdbcTemplate(this.dataSource);
        jdbTemplate.batchUpdate(INSERT_SQL, vehicles, vehicles.size(), this::prepareStatement);
    }

    @Override
    public String getColor(String vehicleNo) {
        var jdbcTemplate = new JdbcTemplate(this.dataSource);
        return jdbcTemplate.queryForObject(SELECT_COLOR_SQL, String.class, vehicleNo);
    }

    @Override
    public int countAll() {
        var jdbcTemplate = new JdbcTemplate(this.dataSource);
        return jdbcTemplate.queryForObject(COUNT_ALL_SQL, Integer.class);
    }

    private Vehicle toVehicle(ResultSet rs) throws SQLException {
        return new Vehicle(rs.getString("VEHICLE_NO"), rs.getString("COLOR"), rs.getInt("WHEEL"), rs.getInt("SEAT"));
    }
    private void prepareStatement(PreparedStatement ps, Vehicle vehicle) throws SQLException {
        ps.setString(1, vehicle.getColor());
        ps.setInt(2, vehicle.getWheel());
        ps.setInt(3, vehicle.getSeat());
        ps.setString(4, vehicle.getVehicleNo());
    }
}
