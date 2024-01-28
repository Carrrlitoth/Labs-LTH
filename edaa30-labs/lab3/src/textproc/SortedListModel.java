package textproc;

import java.util.Comparator;
import java.util.List;

import javax.swing.AbstractListModel;

public class SortedListModel<E> extends AbstractListModel<E> {
	private List<E> list;
	
	public SortedListModel(List<E> list) {
        this.list = list;
    }
	
	public void sort(Comparator<E> comp) {
		list.sort(comp);
		fireContentsChanged(this, 0, list.size());
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public E getElementAt(int index) {
		return list.get(index);
	}
}
