package com.gingermadfire.onlinestore.repository;

import com.gingermadfire.onlinestore.persistence.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {
}
