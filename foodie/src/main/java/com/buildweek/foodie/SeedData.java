package com.buildweek.foodie;

import com.buildweek.foodie.models.*;
import com.buildweek.foodie.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    RestPhotosService restPhotosService;

    @Autowired
    ReviewService reviewService;


    @Override
    public void run(String[] args) throws Exception
    {

//
//
//        Restaurant rest2 = new Restaurant("Bibou", "Philadelphia, PA", "5:30PM - 11PM", "8/10");
//        rest2.getRestphotos()
//             .add(new RestPhotos(rest2,"https://vrconcierge.com/wp-content/uploads/2017/06/8701_bibou-philadelphia-exterior-2.jpg"));
//        rest2.getRestphotos()
//             .add(new RestPhotos(rest2,"https://cdn10.phillymag.com/wp-content/uploads/2013/01/10_Bibou_Mike-Arrison.jpg"));
//        rest2.getRestphotos()
//             .add(new RestPhotos(rest2, "https://s3-media4.fl.yelpcdn.com/bphoto/9C9kBTbfclVmIDpzzXEdFw/l.jpg"));
//        rest2.getReviews()
//             .add(new Reviews(rest2, "French Cuisine", "Veau", "https://www.goodfoodrevolution.com/wp-content/uploads/2012/10/tete-de-veau.jpeg", 27.99, "10/10", "Absolutely delicious! Melt in your mouth veal with fresh vegetables that can not be matched!"));
//        rest2.getReviews()
//             .add(new Reviews(rest2, "French Cuisine", "Espadon", "https://cache.marieclaire.fr/data/photo/w700_c17/43/espadon-aux-raisins-et-aux-.jpg", 32.99, "10/10", "Would come back strickly for this meal everytime. The Espadon was fantastic."));
//        restaurantService.save(rest2);
//
//
//
//        Restaurant rest3 = new Restaurant("Geno's Steaks", "Philadelphia, PA", "24 hours", "9/10");
//        rest3.getRestphotos()
//             .add(new RestPhotos(rest3,"https://upload.wikimedia.org/wikipedia/commons/5/59/Genos_Steaks.JPG"));
//        rest3.getRestphotos()
//             .add(new RestPhotos(rest3,"http://www.jackduggans.com/wp-content/uploads/2017/03/Geno-820x3381.jpg"));
//        rest3.getRestphotos()
//             .add(new RestPhotos(rest3, "https://i.ytimg.com/vi/tH135gUUzFc/maxresdefault.jpg"));
//        rest3.getReviews()
//             .add(new Reviews(rest3,"American Cuisine", "Cheesesteak", "https://www.genosteaks.com/wp-content/uploads/2015/07/product_slide_1.png", 11.00, "10/10", "Best cheesteaks in Philadelphia!"));
//        restaurantService.save(rest3);

        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "password", "Philadelphia", "https://files.slack.com/files-pri/T4JUEB3ME-FN9BMEWQJ/nopath.png", admins);
        u1.getUseremails()
          .add(new Useremail(u1, "admin@email.local"));
        u1.getUseremails()
          .add(new Useremail(u1, "admin@mymail.local"));
        userService.save(u1);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();

        users.add(new UserRoles(new User(), r2));
        User u3 = new User("barnbarn", "ILuvM4th!", "Philadelphia", "https://files.slack.com/files-pri/T4JUEB3ME-FN9BMEWQJ/nopath.png", users);
        u3.getUseremails()
          .add(new Useremail(u3, "barnbarn@email.local"));
        userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
//        ArrayList<Restaurant> rest = new ArrayList<>();
//        rest.add(rest1);
////        rest.add(rest2);
        User u4 = new User("Bob", "password", "New York", "https://files.slack.com/files-pri/T4JUEB3ME-FN9BMEWQJ/nopath.png", users);
        u4.getUseremails()
          .add(new Useremail(u4, "bob@email.local"));
        userService.save(u4);


        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("Jane", "password", "Washington", "https://files.slack.com/files-pri/T4JUEB3ME-FN9BMEWQJ/nopath.png", users);
        userService.save(u5);



        //=================================================================================================//
//        ArrayList<User> usersadd = new ArrayList<>();
//        usersadd.add(u4);
//        Restaurant rest1 = new Restaurant("Bridgeport Family Restaurant", "Lancaster, PA", "6:00AM - 2:00PM", "9/10",usersadd);
//        rest1.getRestphotos()
//             .add(new RestPhotos(rest1,"https://s3-media1.fl.yelpcdn.com/bphoto/hcztsvBKXc9-AmWZRvSUhQ/l.jpg"));
//        rest1.getRestphotos()
//             .add(new RestPhotos(rest1, "https://live.staticflickr.com/702/32136139536_ee7c3c8ef7_b.jpg"));
//        rest1.getReviews()
//             .add(new Reviews( rest1,"American Cuisine", "Breakfast Omelet", "https://tmbidigitalassetsazure.blob.core.windows.net/secure/RMS/attachments/37/1200x1200/exps167311_SD153321B02_04_3b.jpg", 7.50, "8.3/10", "Eggs are unbelievable! Fast time, fresh tomatos with a cheap price."));
//        rest1.getReviews()
//             .add(new Reviews(rest1, "American Cuisine", "Woodsman's Special", "https://www.neighborhoodeals.com/wp-content/uploads/2018/02/breakfast1.jpg", 10.99, "9/10", "Lots of food for a great price! Pancakes lathered in butter with smokey bacon would fill anybody's stomach!"));
//        restaurantService.save(rest1);
////
//
//        Restaurant rest2 = new Restaurant("Bibou", "Philadelphia, PA", "5:30PM - 11PM", "8/10");
//        rest2.getRestphotos()
//             .add(new RestPhotos(rest2,"https://vrconcierge.com/wp-content/uploads/2017/06/8701_bibou-philadelphia-exterior-2.jpg"));
//        rest2.getRestphotos()
//             .add(new RestPhotos(rest2,"https://cdn10.phillymag.com/wp-content/uploads/2013/01/10_Bibou_Mike-Arrison.jpg"));
//        rest2.getRestphotos()
//             .add(new RestPhotos(rest2, "https://s3-media4.fl.yelpcdn.com/bphoto/9C9kBTbfclVmIDpzzXEdFw/l.jpg"));
//        rest2.getReviews()
//             .add(new Reviews(rest2, "French Cuisine", "Veau", "https://www.goodfoodrevolution.com/wp-content/uploads/2012/10/tete-de-veau.jpeg", 27.99, "10/10", "Absolutely delicious! Melt in your mouth veal with fresh vegetables that can not be matched!"));
//        rest2.getReviews()
//             .add(new Reviews(rest2, "French Cuisine", "Espadon", "https://cache.marieclaire.fr/data/photo/w700_c17/43/espadon-aux-raisins-et-aux-.jpg", 32.99, "10/10", "Would come back strickly for this meal everytime. The Espadon was fantastic."));
//        restaurantService.save(rest2);
//
//
//
//        Restaurant rest3 = new Restaurant("Geno's Steaks", "Philadelphia, PA", "24 hours", "9/10");
//        rest3.getRestphotos()
//             .add(new RestPhotos(rest3,"https://upload.wikimedia.org/wikipedia/commons/5/59/Genos_Steaks.JPG"));
//        rest3.getRestphotos()
//             .add(new RestPhotos(rest3,"http://www.jackduggans.com/wp-content/uploads/2017/03/Geno-820x3381.jpg"));
//        rest3.getRestphotos()
//             .add(new RestPhotos(rest3, "https://i.ytimg.com/vi/tH135gUUzFc/maxresdefault.jpg"));
//        rest3.getReviews()
//             .add(new Reviews(rest3,"American Cuisine", "Cheesesteak", "https://www.genosteaks.com/wp-content/uploads/2015/07/product_slide_1.png", 11.00, "10/10", "Best cheesteaks in Philadelphia!"));
//        restaurantService.save(rest3);
//
//

    }
}