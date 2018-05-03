package com.epam.library.dao.impl;

import com.epam.library.constants.LibraryConstants;
import com.epam.library.dao.BaseDAO;
import com.epam.library.domain.*;
import com.epam.library.utils.RequestUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BaseDAO {

	@Override
	public void create(Entity entity) {

		Book book = (Book) entity;
		String request = String.format(
				LibraryConstants.DataBase.Commands.Book.ADD,
				book.getTitle(),
				book.getBrief(),
				book.getAuthor(),
				book.getPublishYear()
		);

		RequestUtils.sendUpdateRequest(request);
	}

	@Override
	public Entity read(int id) {

		String request = String.format(
				LibraryConstants.DataBase.Commands.Book.SELECT_BY_ID,
				id
		);

		ResultSet resultSet = RequestUtils.sendQueryRequest(request);

		Book book = null;
		try {
			if (resultSet != null) {
				while (resultSet.next()) {
					book = new Book(
							resultSet.getInt("book_id"),
							resultSet.getString("title"),
							resultSet.getString("brief"),
							resultSet.getString("author"),
							resultSet.getInt("publish_year")
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return book;
	}

	@Override
	public List<Book> readAll() {

		String request = LibraryConstants.DataBase.Commands.Book.SELECT;

		ResultSet resultSet = RequestUtils.sendQueryRequest(request);

		List<Book> books = new ArrayList<>();
		try {
			if (resultSet != null) {
				while (resultSet.next()) {
					Book book = new Book(
							resultSet.getInt("book_id"),
							resultSet.getString("title"),
							resultSet.getString("brief"),
							resultSet.getString("author"),
							resultSet.getInt("publish_year")
					);
					books.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}

	@Override
	public void update(Entity entity) {

		Book book = (Book) entity;

		String request = String.format(
				LibraryConstants.DataBase.Commands.Book.UPDATE,
				book.getTitle(),
				book.getAuthor(),
				book.getBrief(),
				book.getPublishYear(),
				book.getId()
		);

		RequestUtils.sendUpdateRequest(request);
	}

	@Override
	public void delete(int id) {

		String request = String.format(
				LibraryConstants.DataBase.Commands.Book.DELETE,
				id
		);

		RequestUtils.sendUpdateRequest(request);
	}

}