package top.wexue.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import top.wexue.base.entity.TCorpInfo;
import top.wexue.base.entity.TDepartment;

/**
 * Created by lihb on 8/27/16.
 */
@Repository
public interface CorpInfoRepository extends PagingAndSortingRepository<TCorpInfo,String>,JpaRepository<TCorpInfo,String> {


}
