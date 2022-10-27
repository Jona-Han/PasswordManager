package model;

import exceptions.CollectionIndexOutOfBoundsException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class User implements Writable {
    private static final String JSON_PATH = "./data/data.json";
    private List<Account> accounts;
    private String masterPassword;

    /*
     * EFFECTS: Constructs an empty CollectionOfAccounts with a master password
     */
    public User(String masterPassword) {
        this.masterPassword = masterPassword;
        accounts = new ArrayList<>();
    }

    /*
     * EFFECTS: Constructs a new CollectionOfAccounts with an account and a master password
     */
    public User(String masterPassword, Account account) {
        this.masterPassword = masterPassword;
        accounts = new ArrayList<>();
        accounts.add(account);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds an account to the CollectionOfAccounts, returns true if successfully added
     */
    public boolean add(Account account) {
        return accounts.add(account);
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes an Account object from CollectionOfAccounts, returns true if successfully removed
     */
    public boolean remove(Account account) {
        if (account == null) {
            throw new NullPointerException();
        }
        return accounts.remove(account);
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes an account at the specified index and returns that Account object
     */
    public Account remove(int index) throws CollectionIndexOutOfBoundsException {
        if ((index < 0) || (index >= accounts.size())) {
            throw new CollectionIndexOutOfBoundsException();
        }
        return accounts.remove(index);
    }

    /*
     * EFFECTS: checks if there's an Account object in the CollectionOfAccounts and returns true if there is
     */
    public boolean contains(Account account) {
        if (account == null) {
            throw new NullPointerException();
        }
        return accounts.contains(account);
    }

    /*
     * EFFECTS: returns the size of the CollectionOfAccounts
     */
    public int size() {
        return accounts.size();
    }

    /*
     * EFFECTS: returns an Account at the specified index
     */
    public Account get(int index) throws CollectionIndexOutOfBoundsException {
        if ((index < 0) || (index >= accounts.size())) {
            throw new CollectionIndexOutOfBoundsException();
        }
        return accounts.get(index);
    }

    public List<Account> getInnerList() {
        return accounts;
    }

    public String getMasterPassword() {
        return masterPassword;
    }

    public void setMasterPassword(String newPassword) {
        this.masterPassword = newPassword;
    }

    /*
     * EFFECTS: returns the inner list. Primarily just for testing
     */
    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("masterPassword", masterPassword);
        json.put("accounts", accountsToJson());
        return json;
    }

    /*
     * EFFECTS: returns the accounts in this collection as a JSON Array
     */
    private JSONArray accountsToJson() {
        JSONArray json = new JSONArray();

        for (Account account : accounts) {
            json.put(account.toJSON());
        }
        return json;
    }
}