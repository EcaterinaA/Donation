package ro.ecaterina.donation.service;

import java.util.List;

import ro.ecaterina.donation.model.User;

public interface Service<T> {
	public T read(int id);
	public List<T> readAll();
	public boolean update(int id);
	public boolean delete(int id);
	public boolean create(T object);
}

