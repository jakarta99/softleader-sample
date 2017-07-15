package tw.com.softleader.sample.movie;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MCompanyServiceTest {

	private Logger log = LoggerFactory.getLogger(MCompanyServiceTest.class);
	private MCompanyDao mCompanyDao = new MCompanyDao();
	

	@Test
	public void testCrud() {
		int companySize = mCompanyDao.findAll().size();// 看看幾間公司
		log.debug("Size:" + companySize);

		Movie movie1 = new Movie();// 加一個新人
		movie1.setName("Spider-Man:Home Comming");
		movie1.setPrice("330");
		Movie movie2 = new Movie();
		movie2.setName("Wonder Woman");
		movie2.setPrice("230");

		Collection<Movie> moviesNew = new ArrayList<Movie>();
		moviesNew.add(movie1);// 新人喜歡兩部電影，加進電影List裡
		moviesNew.add(movie2);

		MPerson mPersonNew = new MPerson();
		mPersonNew.setName("Sarah");
		mPersonNew.setIdno("A223456321");
		mPersonNew.setMovies(moviesNew);//新電影
		
		Collection<MPerson> mPersons = new ArrayList<MPerson>();
		mPersons.add(mPersonNew);//新人加入人List

		MCompany mCompanyNew = new MCompany();
		mCompanyNew.setName("iii");		
		mCompanyNew.setMperson(mPersons);
		mCompanyDao.insert(mCompanyNew);
		Long idmn = mCompanyNew.getId();
		log.debug(""+mCompanyDao.findOne(idmn));
		log.debug(""+mCompanyNew);

		Movie movieModify = new Movie();//修改喜歡的電影
		movieModify.setName("Despicable Me3");
		movieModify.setPrice("180");
		movieModify.setmId(mCompanyNew.getMperson().iterator().next().getMovies().iterator().next().getmId());
		moviesNew.add(movieModify);//修改過的電影加入List

		MPerson mPersonModify = new MPerson();//修改人名
		//mPersonModify.setMovies(moviesNew);
		mPersonModify.setName("Diana");
		mPersonModify.setId(mPersonNew.getId());//找出想修改人的cId
		log.debug(""+mPersonNew.getId());
		
		
		mCompanyDao.update(mCompanyNew);
		Long idcn = mCompanyNew.getId();

		mCompanyDao.delete(idcn);

		int companySize1 = mCompanyDao.findAll().size();
		log.debug("Company size:" + companySize1);
		assertEquals(companySize1, companySize);
	}

}