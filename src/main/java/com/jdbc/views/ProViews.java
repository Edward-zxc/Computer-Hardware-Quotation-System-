package com.jdbc.views;

import com.jdbc.beans.*;
import com.jdbc.dao.*;
import com.jdbc.dao.USersDaoImpl.*;
import com.jdbc.utils.Const;

import java.util.List;
import java.util.Scanner;

public class ProViews {
    Scanner in = new Scanner(System.in);
    UserDao usersDao = new UsersDaoImpl();
    GoodsDao goodsDao = new GoodsDaoImpl();
    CartDao cartDao = new CartDaoImpl();
    AdminDao adminDao = new AdminDaoImpl();
    TypeDao typeDao = new TypeDaoImpl();
    AdminLogDao adminLogDao = new AdminLogDaoImpl();
    ShopDetailDao shopDetailDao = new ShopDetailDaoImpl();

    Users users = null;

    private Admin admin = null;

    public static void main(String[] args) {
        ProViews proViews = new ProViews();
        proViews.mainMenu();

    }



    public  void mainMenu() {
        System.out.println("1. 登录  2. 注册  3. 管理员登录  0. 退出");
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                guestLogin();
                break;
            case 2:
                register();
                mainMenu();
                break;
            case 3:
                adminLogin();
                break;
            case 0:
                return;
            default:
                mainMenu();
                break;
        }
    }

    public void adminLogin() {
        System.out.println("请输入用户名：");
        String username = in.next();
        System.out.println("请输入密码：");
        String password = in.next();
        Admin admin1 = adminDao.findAdminByNameAndPwd(username, password);

        if (admin1 != null) {
            System.out.println("欢迎管理员登录！");
            admin = admin1;
            adminMain();
        } else {
            System.out.println("用户名或密码错误！");
        }
    }

    private void adminMain() {
        System.out.println("1. 查看并操作所有用户信息\t2. 查看并操作所有硬件商品信息\t3. 查看并操作所有硬件类型\t4. 查看管理员操作日志\t5. 退出");
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                editAllUsers();
                break;
            case 2:
                editAllGoods();
                break;
            case 3:
                editAllGoodsType();
                break;
            case 4:
                showAllLogs();
                adminMain();
                break;
            case 5:
                mainMenu();
                break;
            default:
                adminMain();
                break;
        }
    }

    private void showAllLogs() {
        List<AdminLog> list = adminLogDao.findAllLogs();
        System.out.println("id\t管理员id\t详细");
        for (AdminLog adminLog : list) {
            System.out.println(adminLog.getId() + "\t" + adminLog.getAid() + "\t\t" + adminLog.getDesc());
        }
    }

    private void editAllGoodsType() {
        showAllGoodsType();
        System.out.println("1. 查看所有商品类型\t2. 修改商品类型信息\t3. 添加商品类型\t4. 删除商品类型\t5. 返回上一级");
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                editAllGoodsType();
                break;
            case 2:
                fixGoodsType();
                editAllGoodsType();
                break;
            case 3:
                addGoodsType();
                editAllGoodsType();
                break;
            case 4:
                deleteGoodsType();
                editAllGoodsType();
                break;
            case 5:
                adminMain();
                break;
            default:
                editAllGoodsType();
                break;
        }
    }

    private void deleteGoodsType() {
        System.out.println("请输入你要删除的类型的id");
        int id = in.nextInt();
        Type type = typeDao.findTypeById(id);
        if (type != null) {
            String typeName = type.getTypename();
            int rows = typeDao.deleteType(type);
            if (rows != -1) {
                System.out.println("删除成功！");
                AdminLog adminLog = new AdminLog();
                adminLog.setAid(admin.getId());
                adminLog.setDesc("删除一个新商品类型：" + typeName);
                adminLogDao.addDesc(adminLog);
            } else {
                System.out.println("删除失败！");
            }
        } else {
            System.out.println("未找到该类型！");
        }
    }

    private void addGoodsType() {
        System.out.println("请输入新的类型名称：");
        String typeName = in.next();
        Type type = new Type();
        type.setTypename(typeName);
        int rows = typeDao.addType(type);
        if (rows != -1) {
            System.out.println("添加成功！");
            AdminLog adminLog = new AdminLog();
            adminLog.setAid(admin.getId());
            adminLog.setDesc("添加一个新商品类型：" + typeName);
            adminLogDao.addDesc(adminLog);
        } else {
            System.out.println("添加失败！");
        }
    }

    private void fixGoodsType() {
        System.out.println("请输入类型id：");
        int id = in.nextInt();
        Type type = typeDao.findTypeById(id);
        if (type != null) {
            String oldName = type.getTypename();
            System.out.println("请输入新的类型名称：");
            String typeName = in.next();
            type.setTypename(typeName);
            int rows = typeDao.updateTypeName(type);
            if (rows != -1) {
                System.out.println("修改成功！");
                AdminLog adminLog = new AdminLog();
                adminLog.setAid(admin.getId());
                adminLog.setDesc("将\"" + oldName + "\"修改为新的商品类型名称：" + typeName);
                adminLogDao.addDesc(adminLog);
            } else {
                System.out.println("修改失败！");
            }
        } else {
            System.out.println("未找到该商品类型！");
        }
    }

    private void showAllGoodsType() {
        List<Type> list = typeDao.findAllTypes();
        System.out.println("类型id\t类型名称");
        for (Type type : list) {
            System.out.println(type.getTypeid() + "\t\t" + type.getTypename());
        }
    }

    private void editAllGoods() {
        showGoods();
        System.out.println("1. 查看所有商品\t2. 修改商品信息\t3. 添加商品\t4. 删除商品\t5. 返回上一级");
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                editAllGoods();
                break;
            case 2:
                fixGoods();
                editAllGoods();
                break;
            case 3:
                addGoods();
                editAllGoods();
                break;
            case 4:
                deleteGoods();
                editAllGoods();
                break;
            case 5:
                adminMain();
                break;
            default:
                editAllGoods();
                break;
        }
    }

    private void deleteGoods() {
        System.out.println("请输入硬件id");
        int id = in.nextInt();
        Goods goods = goodsDao.findById(id);
        if (goods != null) {
            String goodsName = goods.getGname();
            int rows = goodsDao.deleteGoodsById(id);
            if (rows != -1) {
                System.out.println("删除成功！");
                AdminLog adminLog = new AdminLog();
                adminLog.setAid(admin.getId());
                adminLog.setDesc("删除一个硬件：" + goodsName);
                adminLogDao.addDesc(adminLog);
            } else {
                System.out.println("删除失败！");
            }
        } else {
            System.out.println("硬件不存在！");
        }
    }

    private void addGoods() {
        System.out.println("请输入硬件名称：");
        String name = in.next();
        System.out.println("请输入硬件数量：");
        int num = in.nextInt();
        System.out.println("请输入硬件价格：");
        double price = in.nextDouble();
        System.out.println("请输入硬件类型：");
        String typeName = in.next();
        Type type = typeDao.findTypeByName(typeName);
        if (type != null) {
            Goods goods = new Goods();
            goods.setGname(name);
            goods.setGnum(num);
            goods.setGprice(price);
            goods.setTypeid(type.getTypeid());
            int rows = goodsDao.addGoods(goods);
            if (rows != -1) {
                System.out.println("添加成功！");
                AdminLog adminLog = new AdminLog();
                adminLog.setAid(admin.getId());
                adminLog.setDesc("添加一个商品：" + name);
                adminLogDao.addDesc(adminLog);
            } else {
                System.out.println("添加失败！");
            }
        } else {
            System.out.println("未找到该类型是否添加？y/n");
            String choice = in.next();
            if ("y".equals(choice)) {
                Type type1 = new Type();
                type1.setTypename(typeName);
                typeDao.addType(type1);
                Goods goods = new Goods();
                goods.setGname(name);
                goods.setGnum(num);
                goods.setGprice(price);
                goods.setTypeid(type1.getTypeid());
                int rows = goodsDao.addGoods(goods);
                if (rows != -1) {
                    System.out.println("添加成功！");
                    AdminLog adminLog = new AdminLog();
                    adminLog.setAid(admin.getId());
                    adminLog.setDesc("添加一个新商品类型：" + typeName);
                    adminLogDao.addDesc(adminLog);
                } else {
                    System.out.println("添加失败！");
                }
            } else {
                System.out.println("商品添加失败！");
            }
        }

    }

    private void fixGoods() {
        System.out.println("请输入商品id");
        int id = in.nextInt();
        Goods goods = goodsDao.findById(id);
        if (goods != null) {
            System.out.println("是否修改商品名称？y/n");
            String choice = in.next();
            if ("y".equals(choice)) {
                System.out.println("请输入新名称：");
                String name = in.next();
                goods.setGname(name);
            }
            System.out.println("是否修改商品数量？y/n");
            choice = in.next();
            if ("y".equals(choice)) {
                System.out.println("请输入新数量：");
                int num = in.nextInt();
                goods.setGnum(num);
            }
            System.out.println("是否修改商品价格？y/n");
            choice = in.next();
            if ("y".equals(choice)) {
                System.out.println("请输入新的价格：");
                double price = in.nextDouble();
                goods.setGprice(price);
            }
            System.out.println("是否修改商品类型？y/n");
            choice = in.next();
            if ("y".equals(choice)) {
                System.out.println("请输入新的商品类型id");
                int typeid = in.nextInt();
                goods.setTypeid(typeid);
            }
            int rows = goodsDao.updateGoods(goods);
            if (rows != -1) {
                System.out.println("修改成功！");
                AdminLog adminLog = new AdminLog();
                adminLog.setAid(admin.getId());
                adminLog.setDesc("修改" + goods.getGname() + "的商品信息");
                adminLogDao.addDesc(adminLog);
            } else {
                System.out.println("修改失败！");
            }
        } else {
            System.out.println("未找到该商品信息！");
        }
    }

    private void editAllUsers() {
        showAllUsers();
        System.out.println("1. 查看所有用户\t2. 修改用户状态\t3. 添加用户\t4. 删除用户\t5. 返回上一级");
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                editAllUsers();
                break;
            case 2:
                fixUsers();
                editAllUsers();
                break;
            case 3:
                register();
                editAllUsers();
                break;
            case 4:
                deleteUsers();
                editAllUsers();
            case 5:
                adminMain();
                break;
            default:
                editAllUsers();
                break;
        }
    }

    private void deleteUsers() {
        System.out.println("请输入用户id");
        int id = in.nextInt();
        Users users = usersDao.findUserById(id);
        if (users != null) {
            int rows = usersDao.deleteUsers(id);
            if (rows != -1) {
                System.out.println("删除成功！");
                AdminLog adminLog = new AdminLog();
                adminLog.setAid(admin.getId());
                adminLog.setDesc("删除一个用户：" + users.getUname());
                adminLogDao.addDesc(adminLog);
            } else {
                System.out.println("删除失败！");
            }
        } else {
            System.out.println("用户不存在！");
        }
    }

    private void fixUsers() {
        System.out.println("1.修改用户状态\t2. 返回上一级");
        int choice = in.nextInt();
        if (choice == 1) {
            System.out.println("请输入用户id");
            int id = in.nextInt();
            Users users = usersDao.findUserById(id);
            if (users != null) {
                if (users.getUstatus().equals(Const.USERON)) {
                    users.setUstatus(Const.USEROFF);
                } else {
                    users.setUstatus(Const.USERON);
                }
                int rows = usersDao.updateUsers(users);
                if (rows != -1) {
                    System.out.println("修改成功！");
                    AdminLog adminLog = new AdminLog();
                    adminLog.setAid(admin.getId());
                    adminLog.setDesc("修改用户状态：" + users.getUname());
                    adminLogDao.addDesc(adminLog);
                } else {
                    System.out.println("修改失败！");
                }
            } else {
                System.out.println("未找到该用户！");
            }
        }
    }

    private void showAllUsers() {
        List<Users> list = usersDao.findAll();
        System.out.println("id\t用户名\t\t密码\t\t\t\t状态");
        for (Users u : list) {
            System.out.println(u.getId() + "\t" + u.getUname() + "\t\t" + u.getUpwd() + "\t\t\t" + u.getUstatus());
        }
    }

    private void register() {
        System.out.println("请输入用户名：");
        String usm = in.next();
        Users users = usersDao.findUserByName(usm);
        if (users == null) {
            System.out.println("请输入密码：");
            String pwd = in.next();
            if (pwd.length() < 6 || pwd.length() > 18) {
                System.out.println("密码长度有误，请重新输入！");
                System.out.println("请输入密码：");
                pwd = in.next();
                users = new Users();
                users.setUname(usm);
                users.setUpwd(pwd);
                users.setUstatus(Const.USERON);
                int rows = usersDao.addUsers(users);
                System.out.println(rows != -1 ? "注册成功！" : "注册失败！");
            } else {
                users = new Users();
                users.setUname(usm);
                users.setUpwd(pwd);
                users.setUstatus(Const.USERON);
                int rows = usersDao.addUsers(users);
                System.out.println(rows != -1 ? "注册成功！" : "注册失败！");
            }
        } else {
            System.out.println("此用户名已存在，请重新注册！");
            register();
        }
    }

    private void guestLogin() {
        System.out.println("请输入用户名：");
        String name = in.next();
        System.out.println("请输入密码：");
        String pwd = in.next();
        users = usersDao.findUserByNameAndPwd(name, pwd);
        if (users != null) {
            if (users.getUstatus().equals(Const.USERON)) {
                guestMenu();
            } else {
                System.out.println("账户被禁用，请联系管理员！");
            }
        } else {
            System.out.println("未找到该用户");
        }
        mainMenu();
    }

    private void guestMenu() {
        System.out.println("1. 查看硬件信息\t2. 查看购物车\t3. 查看订单\t4. 查看个人信息\t5. 修改密码\t6. 存款\t7. 取款\t8. 返回主菜单");
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                showGoods();
                cartMenu();
                break;
            case 2:
                showCard();
                guestMenu();
                break;
            case 3:
                showShopDetail();
                guestMenu();
                break;
            case 4:
                System.out.println("用户名\t密码");
                System.out.println(users.getUname() + "\t" + users.getUpwd());
                guestMenu();
                break;
            case 5:
                System.out.println("请输入新密码：");
                String pwd = in.next();
                users.setUpwd(pwd);
                int rows = usersDao.updateUsers(users);
                if (rows != -1) {
                    System.out.println("修改成功，请重新登陆！");
                    guestLogin();
                } else {
                    System.out.println("修改失败！");
                    guestMenu();
                }
                break;
            case 6:
                deposit();
                guestMenu();
                break;
            case 7:
                withdraw();
                guestMenu();
                break;
            case 8:
                mainMenu();
                break;
            default:
                guestMenu();
                break;
        }
    }

    private void deposit() {
        if (users == null) {
            System.out.println("错误：Users 对象为 null。请在调用 deposit 之前初始化它。");
            return;
        }

        while (true) {
            System.out.println("您当前账户余额：" + users.getUscore());
            System.out.println("请输入您的存款金额：");
            double money = in.nextDouble();

            if (money < 0 || money >= 200000) {
                System.out.println("非法数字，请重新输入！");
            } else {
                users.setUscore(users.getUscore() + money);
                int rows = usersDao.updateUsers(users);
                System.out.println(rows != -1 ? "存款成功！" : "存款失败，请联系管理员！");
                break;  // 跳出循环，表示成功存款
            }
        }
    }

    private void withdraw() {
        System.out.println("您当前账户余额：" + users.getUscore());
        System.out.println("请输入您的取款金额：");
        double money = in.nextDouble();
        if (money < 0 || money >= 200000) {
            System.out.println("非法数字，请重新输入！");
            withdraw();
        } else {
            if (money < users.getUscore()) {
                users.setUscore(users.getUscore() - money);
                int rows = usersDao.updateUsers(users);
                System.out.println(rows != -1 ? "取款成功！" : "取款失败，请联系管理员！");
            } else {
                System.out.println("余额不足！");
            }
        }
    }

    private void showShopDetail() {
        List<ShopDetail> list = shopDetailDao.findAllShopDetailByUid(users.getId());
        System.out.println("id\t消费金额\t消费时间");
        for (ShopDetail shopDetail : list) {
            System.out.println(shopDetail.getId() + "\t" + shopDetail.getCost() + "\t" + shopDetail.getTime());
        }
    }

    private void showGoods() {
        List<Goods> list = goodsDao.findAll();
        System.out.println("id\t硬件名称\t硬件数量\t\t硬件价格\t\t硬件类型id");
        for (Goods g : list) {
            System.out.println(g.getGid() + "\t" + g.getGname() + " \t" + g.getGnum() + " \t\t" + g.getGprice() + "  \t\t" + g.getTypeid());
        }
    }

    private void cartMenu() {
        System.out.println("1. 添加购物车\t2. 查看购物车\t3. 删除购物车中的商品\t4. 结账\t0. 返回上一级");
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                addCard();
                break;
            case 2:
                showCard();
                cartMenu();
                break;
            case 3:
                deleteCard();
                cartMenu();
                break;
            case 4:
                pay();
                cartMenu();
                break;
            case 0:
                guestMenu();
                break;
            default:
                cartMenu();
                break;
        }
    }

    private void pay() {
        double sum = showCard();
        double money = users.getUscore();
        boolean status = true;
        // 付款成功后，减少用户账户余额，清空购物车，减少商品信息中商品的数量，添加购物明细
        if (money > sum) {
            System.out.println("付款成功！找您：" + (money - sum));
            // 清空购物车，判断商品数量是否足够
            List<Cart> list = cartDao.findAllByUid(users.getId());
            for (Cart cart : list) {
                int goodsId = cart.getGoodsid();
                int goodsNum = cart.getCnum();
                Goods goods = goodsDao.findById(goodsId);
                if (goods.getGnum() > goodsNum) {
                    cartDao.delete(cart.getCid());
                    goods.setGnum(goods.getGnum() - goodsNum);
                    // 减少商品信息中的商品数量
                    goodsDao.updateGoods(goods);
                } else {
                    System.out.println("库存不足请联系管理员");
                    status = false;
                    break;
                }
            }
            if (status) {
                users.setUscore(money - sum);
                // 更新账户余额
                usersDao.updateUsers(users);
                ShopDetail shopDetail = new ShopDetail();
                shopDetail.setCost(sum);
                shopDetail.setUid(users.getId());
                int rows = shopDetailDao.addShopDetail(shopDetail);
                System.out.println(rows != -1 ? "结算成功！" : "结算失败！");
            }
        } else {
            System.out.println("余额不足，请充值后重新支付！");
        }
    }

    private void deleteCard() {
        System.out.println("请输入要删除的购物车id");
        int id = in.nextInt();
        Cart cart = cartDao.findByUidAndGid(users.getId(), id);
        if (cart != null) {
            int rows = cartDao.delete(id);
            System.out.println(rows != -1 ? "删除成功！" : "删除失败！");
        } else {
            System.out.println("您的购物车没有该硬件！");
        }
    }

    private double showCard() {
        List<Cart> list = cartDao.findAllByUid(users.getId());
        double sum = 0;
        System.out.println("id\t\t硬件名称\t\t硬件数量\t硬件价格");
        for (Cart c : list) {
            System.out.println(c.getCid() + "\t\t" + c.getGname() + "  \t\t" + c.getCnum() + "\t\t" + c.getGprice());
            sum += c.getTotalPrice();
        }
        System.out.println("------------------------------------");
        System.out.println("\t\t\t\t\t总计：" + sum);
        return sum;
    }

    private void addCard() {
        System.out.println("请输入硬件编号：");
        int id = in.nextInt();
        Goods goods = goodsDao.findById(id);
        if (goods != null) {
            if (users != null) {
                // 根据uid和gid判断cart
                Cart cart = cartDao.findByUidAndGid(users.getId(), goods.getGid());
                if (cart != null) {
                    int rows = cartDao.update(cart);
                    System.out.println(rows != -1 ? "添加成功！" : "添加失败");
                } else {
                    cart = new Cart();
                    cart.setUserid(users.getId());
                    cart.setCnum(1);
                    cart.setGoodsid(goods.getGid());
                    int rows = cartDao.save(cart);
                    System.out.println(rows != -1 ? "添加成功！" : "添加失败");
                }
            } else {
                System.out.println("非法访问！");
            }
        } else {
            System.out.println("硬件编号错误！");
        }
        cartMenu();
    }

}
