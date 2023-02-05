import java.util.*;

/*
    @author: Aman Upadhyay
    @date: 03/02/2023
*/

class Market {
    String productName;
    int productPrice;
    int productQuantity;

    Market(String productName, int productPrice, int productQuantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void display() {
        System.out.println("Product Name: " + productName);
        System.out.println("Product Price: " + productPrice);
        System.out.println("Product Quantity: " + productQuantity);
    }
}

public class ProMarket {
    public static void Menu() {
        System.out.println("\nMenu");
        System.out.println("1. View Products");
        System.out.println("2. Add to Cart");
        System.out.println("3. View Cart");
        System.out.println("4. Delete from Cart");
        System.out.println("5. Generate Bill");
        System.out.println("6. Exit");
    }

    public static void ViewProducts(Market[] products) {
        System.out.println("\n<--     Welcome to the Market      -->");
        System.out.println("Sno \tProducts \tPrice \tQuantity");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". \t" + products[i].getProductName() + "      \t"
                    + products[i].getProductPrice() + "\t" + products[i].getProductQuantity());
        }
    }

    public static void AddToCart(Market[] products, Market[] cart, int cartCount) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the product number you want to add to cart: ");
        int productNumber = input.nextInt();
        System.out.print("Enter the quantity you want to add to cart: ");
        int productQuantity = input.nextInt();
        if (productQuantity > products[productNumber - 1].getProductQuantity()) {
            System.out.println("Sorry, we don't have that much quantity");
        } else {
            if (cart[productNumber - 1] == null) {
                cart[productNumber - 1] = new Market(products[productNumber - 1].getProductName(),
                        products[productNumber - 1].getProductPrice(), productQuantity);
                cartCount++;
            } else {
                cart[productNumber - 1]
                        .setProductQuantity(cart[productNumber - 1].getProductQuantity() + productQuantity);
            }
            products[productNumber - 1]
                    .setProductQuantity(products[productNumber - 1].getProductQuantity() - productQuantity);
        }
        input.close();
    }

    public static void DeleteFromCart(Market[] products, Market[] cart, int cartCount) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the product number you want to delete from cart: ");
        int productNumber = input.nextInt();
        System.out.print("Enter the quantity you want to delete from cart: ");
        int productQuantity = input.nextInt();
        if (productQuantity > cart[productNumber - 1].getProductQuantity()) {
            System.out.println("Sorry, we don't have that much quantity");
        } else {
            products[productNumber - 1]
                    .setProductQuantity(products[productNumber - 1].getProductQuantity() + productQuantity);
            cart[productNumber - 1]
                    .setProductQuantity(cart[productNumber - 1].getProductQuantity() - productQuantity);
            if (cart[productNumber - 1].getProductQuantity() == 0) {
                cart[productNumber - 1] = null;
                cartCount--;
            }
        }
        input.close();
    }

    public static void ViewCart(Market[] cart) {
        System.out.println("\n<--     Welcome to the Cart      -->");
        System.out.println("Sno \tProducts \tPrice \tQuantity");
        for (int i = 0; i < cart.length; i++) {
            if (cart[i] != null) {
                System.out.println((i + 1) + ". \t" + cart[i].getProductName() + "      \t"
                        + cart[i].getProductPrice() + "\t" + cart[i].getProductQuantity());
            }
        }
    }

    public static void GenerateBill(Market[] cart) {
        int total = 0;
        System.out.println("\n<--     Welcome to the Bill      -->");
        System.out.println("Sno \tProducts \tPrice \tQuantity");
        for (int i = 0; i < cart.length; i++) {
            if (cart[i] != null) {
                System.out.println((i + 1) + ". \t" + cart[i].getProductName() + "      \t"
                        + cart[i].getProductPrice() + "\t" + cart[i].getProductQuantity());
                total += cart[i].getProductPrice() * cart[i].getProductQuantity();
            }
        }
        System.out.println("-------------------------------->");
        System.out.println("Total Payable: " + total);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int totalNumberOfProducts = 0;
        System.out.println("\nHey Admin, Please add some products to the market!");
        System.out.print("Enter the total number of products you want to add: ");
        totalNumberOfProducts = input.nextInt();

        /* Adding Products to Market */
        Market[] products = new Market[totalNumberOfProducts];
        for (int i = 0; i < products.length; i++) {
            System.out.println("\n---------------->\nEnter the details of product: " + (i + 1));
            System.out.print("Enter the product name: ");
            String productName = input.next();
            System.out.print("Enter the product price: ");
            int productPrice = input.nextInt();
            System.out.print("Enter the product quantity: ");
            int productQuantity = input.nextInt();
            products[i] = new Market(productName, productPrice, productQuantity);
        }

        /* Cart */
        Market[] cart = new Market[totalNumberOfProducts];
        int cartCount = 0;

        /* Menu Driven */
        int choice = 0;
        do {
            Menu();
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    ViewProducts(products);
                    break;
                case 2:
                    AddToCart(products, cart, cartCount);
                    break;
                case 3:
                    ViewCart(cart);
                    break;
                case 4:
                    DeleteFromCart(products, cart, cartCount);
                    break;
                case 5:
                    System.out.println("Generating Bill...");
                    GenerateBill(cart);
                    break;
                case 6:
                    System.out.println("Thank you for shopping with us");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 6);
        input.close();
    }
}
