import { BaseInputProps } from './BaseInputProps'

export function BaseInput({ ...rest }: BaseInputProps) {
  return (
    <input
      {...rest}
      className="h-12 rounded-[10px] border border-[#d2d2d2] px-[10px] py-[8px] font-poppins text-sm outline-none transition-[border-color,box-shadow] duration-300 placeholder:text-sm placeholder:text-[#636363] placeholder:font-['system-ui'] placeholder:transition-[padding-left] placeholder:duration-300 focus:border-[var(--color-01)] focus:shadow-[0_3px_10px_0_rgba(34,41,47,0.1)] focus:placeholder:pl-1.5 read-only:bg-[#d2d2d2] read-only:opacity-70"
    />
  )
}
