package net.FAP;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctionFAPService {
	@Autowired
	private FunctionFAPRepository repo;

	public List<FunctionFAP> listAll() {
		Iterable<FunctionFAP> i = repo.findAll();
		List<FunctionFAP> list = new ArrayList<FunctionFAP>();
		for(FunctionFAP e: i) {
			list.add(e);
		}
		return list;
	}

	public void save(FunctionFAP FunctionFAP) {
		repo.save(FunctionFAP);
	}

	public FunctionFAP get(Long id) {
		return repo.findById(id).get();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}