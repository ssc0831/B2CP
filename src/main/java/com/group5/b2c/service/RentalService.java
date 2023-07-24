package com.group5.b2c.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.group5.b2c.model.Book;
import com.group5.b2c.model.Member;
import com.group5.b2c.model.Rental;
import com.group5.b2c.repository.BookRepository;
import com.group5.b2c.repository.RentalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalService {
	private final BookRepository bookRepository;
	private final RentalRepository rentalRepository;
	
	public void requestBook(long bookid, Member loginMember) {
		Book book = bookRepository.findById(bookid).get();
		Rental rental = new Rental();
		rental.setBookid(book);
		rental.setReturnid(loginMember);
		rental.setRentid(book.getMemberid());
		rental.setRentstatus("request");
		rentalRepository.save(rental);	
	}
	public List<Book> rentlist(Member member){
		return bookRepository.findByMemberid(member);
	}
	@Transactional
	public void acceptrent(long id) {
		Rental rental = new Rental();
		rental = rentalRepository.findById(id).get();
		rental.setRentstatus("rent");
	}
}
