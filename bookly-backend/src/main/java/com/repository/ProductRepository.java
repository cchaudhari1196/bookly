package com.repository;

import com.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "select p from Product p join p.categories c where c.c_name=?1")
	public List<Product> getByCategoryName( String name);

	@Query(value = "select p from Product p join p.categories c where c.c_id=?1")
	public List<Product> getByCategoryId( int cId);

	@Query(value = "select * from product where pname=?1 or pdesc=?2", nativeQuery = true)
	public List<Product> searchbykeyword(String pname, String pdesc);

	@Query(value = "select * from product where c_id in(select c_id from category where c_type='STITCHED')", nativeQuery = true)
	public List<Product> getAllStitched();

	@Query(value = "select * from product where v_id =?1", nativeQuery = true)
	public List<Product> getByVid(int v_id);


	@Query(value = "select * from product where v_id =?1", nativeQuery = true)
	public List<Product> search(int v_id);

	@Modifying
	@Query(value = "delete from productaudit where pid=?1", nativeQuery = true)
	public void productAudit(int pid);

	@Modifying
	@Query(value = "update product set papprove='true',pprice=pprice*0.1 where p_id=?1", nativeQuery = true)
	public int productadd(int p_id);

	@Modifying
	@Query(value = "delete from product where p_id=?1", nativeQuery = true)
	public void productdel(int p_id);

	@Query(value = "select c_id from category where c_type=?1 AND c_name=?2", nativeQuery = true)
	public int cidReturn(String c_type, String c_name);

//	@Modifying
//	@Query(value = "insert into product(c_id,v_id,pname,pdesc,psize,pbrand,pprice,pqty,prating,image_url)	values(?1,?2,?3,?4,?5,?6,?7,?8,3,?9)", nativeQuery = true)
//	public int vaddproduct(int c_id, int v_id, String pname, String pdesc, String psize, String pbrand, float pprice,
//						   int pqty, String image_url);

	@Query(value = "select * from product where pqty=0 AND v_id=?1", nativeQuery = true)
	public List<Product> viewOutOfStock(int v_id);

	@Query(value = "select * from product where p_id=?1", nativeQuery = true)
	public List<Product> getproducts(int p_id);
//
//	@Modifying
//	@Query(value = "update admin set awallet=awallet-(?1*?2) where adminid=121", nativeQuery = true)
//	public void pdadminwallet(float pprice, int pqty);

}
