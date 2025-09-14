package com.city.repository;
import com.city.model.Citizen;
import com.city.repository.memory.InMemoryCitizenRepository;
import org.testng.Assert;
import org.testng.annotations.Test;
public class CitizenRepositoryTest {
    @Test(groups = "repo")
    public void saveAndFind_ok() {
        var repo = new InMemoryCitizenRepository();
        var c = new Citizen("U100", "Akash");
        repo.save(c);
        Assert.assertTrue(repo.findById("U100").isPresent());
        Assert.assertEquals(repo.findById("U100").get().getName(), "Akash");
        Assert.assertEquals(repo.findAll().size(), 1);
    }
}