package seedu.address.model.beneficiary;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.beneficiary.exceptions.BeneficiaryNotFoundException;
import seedu.address.model.beneficiary.exceptions.DuplicateBeneficiaryException;

/**
 * A list of beneficiaries that enforces uniqueness between its elements and does not allow nulls.
 * A Beneficiary is considered unique by comparing using {@code Beneficiary#isSameBeneficiary(Beneficiary)}. As such,
 * adding and updating of Beneficiaries uses Beneficiary#isSameBeneficiary(Beneficiary) for equality so as to ensure
 * that the Beneficiary being added or updated is unique in terms of identity in the UniqueBeneficiaryList. However,
 * the removal of a Beneficiary uses Beneficiary#equals(Object) so as to ensure that the Beneficiary with exactly
 * the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Beneficiary#isSameBeneficiary(Beneficiary)
 */
public class UniqueBeneficiaryList implements Iterable<Beneficiary> {

    private final ObservableList<Beneficiary> internalList = FXCollections.observableArrayList();
    private final ObservableList<Beneficiary> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Beneficiary as the given argument.
     */
    public boolean contains(Beneficiary toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameBeneficiary);
    }

    /**
     * Adds a Beneficiary to the list.
     * The Beneficiary must not already exist in the list.
     */
    public void add(Beneficiary toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateBeneficiaryException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Beneficiary {@code target} in the list with {@code editedBeneficiary}.
     * {@code target} must exist in the list.
     * The Beneficiary identity of {@code editedBeneficiary} must not be the same as another existing
     * Beneficiary in the list.
     */
    public void setBeneficiary(Beneficiary target, Beneficiary editedBeneficiary) {
        requireAllNonNull(target, editedBeneficiary);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BeneficiaryNotFoundException();
        }

        if (!target.isSameBeneficiary(editedBeneficiary) && contains(editedBeneficiary)) {
            throw new DuplicateBeneficiaryException();
        }

        internalList.set(index, editedBeneficiary);
    }

    public Beneficiary getBeneficiaryInIndex (Index targetIndex) {
        return internalList.get(targetIndex.getZeroBased());
    }

    /**
     * Removes the equivalent Beneficiary from the list.
     * The Beneficiary must exist in the list.
     */
    public void remove(Beneficiary toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new BeneficiaryNotFoundException();
        }
    }

    public void setBeneficiaries(UniqueBeneficiaryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code beneficiaries}.
     * {@code beneficiaries} must not contain duplicate beneficiaries.
     */
    public void setBeneficiaries(List<Beneficiary> beneficiaries) {
        requireAllNonNull(beneficiaries);
        if (!beneficiariesAreUnique(beneficiaries)) {
            throw new DuplicateBeneficiaryException();
        }

        internalList.setAll(beneficiaries);
    }
    /**
     * Returns true if {@code beneficiaries} contains only unique beneficiaries.
     */
    private boolean beneficiariesAreUnique(List<Beneficiary> beneficiaries) {
        for (int i = 0; i < beneficiaries.size() - 1; i++) {
            for (int j = i + 1; j < beneficiaries.size(); j++) {
                if (beneficiaries.get(i).isSameBeneficiary(beneficiaries.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Beneficiary> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Beneficiary> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueBeneficiaryList // instanceof handles nulls
                && internalList.equals(((UniqueBeneficiaryList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
